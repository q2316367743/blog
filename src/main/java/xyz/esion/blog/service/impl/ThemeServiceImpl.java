package xyz.esion.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.esion.blog.constant.FolderConstant;
import xyz.esion.blog.constant.PathConstant;
import xyz.esion.blog.entity.Theme;
import xyz.esion.blog.enumeration.ThemeTypeEnum;
import xyz.esion.blog.mapper.ThemeMapper;
import xyz.esion.blog.service.ThemeService;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Esion
 * @since 2021/12/24
 */
@Service
@Slf4j
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements ThemeService{

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Theme entity) {
        // note: 如果是远程目录，先同步，再新增
        if (entity.getType().equals(ThemeTypeEnum.REMOTE.getValue())) {
            try {
                Git.cloneRepository()
                        .setURI(entity.getSource())
                        .setDirectory(new File(PathConstant.THEMES_PATH, entity.getName()))
                        .setBranch("master")
                        .call()
                        .close();
            }catch (Exception e) {
                log.error("新增主题，clone错误");
                log.error(e.getMessage(), e);
                throw new IllegalArgumentException("clone失败");
            }
        }else if (entity.getType().equals(ThemeTypeEnum.LOCAL.getValue())) {
            // note: 如果是本地目录，首先确认目录是否存在，之后确定目录有没有模板目录
            File file = new File(entity.getSource());
            if (!file.exists()) {
                throw new IllegalArgumentException("目录不存在");
            }
            if (!FileUtil.exist(new File(file, FolderConstant.TEMPLATES_FOLDER))) {
                throw new IllegalArgumentException("目录下不存在" + FolderConstant.TEMPLATES_FOLDER + "文件夹");
            }
        }else {
            throw new IllegalArgumentException("类型错误");
        }
        entity.setSyncTime(new Date());
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        Theme theme = getById(id);
        if (theme == null) {
            throw new IllegalArgumentException("主题不存在");
        }
        if (theme.getIsEnable().equals(1)) {
            // 启用的主题无法删除
            throw new IllegalArgumentException("主题已启用，请启用其他主题后删除该主题");
        }
        if (theme.getType().equals(ThemeTypeEnum.LOCAL.getValue())) {
            // 本地目录如果在主题目录，则删除
            if (theme.getSource().startsWith(PathConstant.THEMES_PATH)) {
                FileUtil.del(theme.getSource());
            }
        }else if (theme.getType().equals(ThemeTypeEnum.REMOTE.getValue())) {
            // 远程目录直接删除
            FileUtil.del(new File(PathConstant.THEMES_PATH, theme.getName()));
        }
        // 删除文件夹
        super.updateById(Theme.builder()
                .id((Integer) id)
                .name(theme.getName() + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
                .build());
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean enable(Integer id) {
        //先判断ID是否存在
        Theme theme = getById(id);
        if (theme == null) {
            throw new IllegalArgumentException("主题不存在");
        }
        // 如果已经启用，直接返回
        if (theme.getIsEnable().equals(1)) {
            return true;
        }
        // 执行操作
        // 本地直接复制
        File local;
        if (theme.getType().equals(ThemeTypeEnum.LOCAL.getValue())) {
            local = new File(theme.getSource());
            if (!local.exists()) {
                throw new IllegalArgumentException("目录不存在");
            }
        }
        else if (theme.getType().equals(ThemeTypeEnum.REMOTE.getValue())) {
            // 远程
            local = new File(PathConstant.THEMES_PATH, theme.getName());
            if (!local.exists()) {
                // 不存在则同步
                this.sync(id);
            }

        }else {
            throw new IllegalArgumentException("主题类型错误");
        }
        if (!local.isDirectory()) {
            throw new IllegalArgumentException("路径不是目录");
        }
        if (local.listFiles() == null) {
            throw new IllegalArgumentException("路径不是目录");
        }
        if (Objects.requireNonNull(local.listFiles()).length == 0) {
            throw new IllegalArgumentException("目录下没有文件夹");
        }
        File templates = null;
        File staticFolder = null;
        for (File file : Objects.requireNonNull(local.listFiles())) {
            if (file.getName().equals(FolderConstant.TEMPLATES_FOLDER)) {
                templates = file;
            }
            if (file.getName().equals(FolderConstant.STATIC_FOLDER)) {
                staticFolder = file;
            }
        }
        if (templates == null) {
            throw new IllegalArgumentException("主题文件夹不存在");
        }
        // 删除原目录
        FileUtil.del(PathConstant.TEMPLATES_PATH);
        FileUtil.del(PathConstant.STATIC_PATH);
        FileUtil.copy(templates, new File(PathConstant.THEME_PATH), true);
        if (staticFolder != null) {
            FileUtil.copy(staticFolder, new File(PathConstant.THEME_PATH), true);
        }
        // 先禁用全部已启用的
        update(Theme.builder().isEnable(0).build(), new UpdateWrapper<Theme>().eq("is_enable", 1));
        // 将这个设为启用
        updateById(Theme.builder().id(id).isEnable(1).enableTime(new Date()).build());
        return true;
    }

    @Override
    public Boolean sync(Integer id) {
        // 获取主题
        Theme theme = this.getById(id);
        // 判断类型
        if (theme.getType().equals(ThemeTypeEnum.LOCAL.getValue())) {
            // 本地：
            throw new IllegalArgumentException("本地目录无法同步");
        }else if (theme.getType().equals(ThemeTypeEnum.REMOTE.getValue())) {
            // 判断目录
            File remote = new File(PathConstant.THEMES_PATH, theme.getName());
            if (remote.exists()) {
                if (!remote.isDirectory()) {
                    boolean delete = remote.delete();
                    if (!delete) {
                        throw new IllegalArgumentException("路径不是目录，且删除失败");
                    }
                    // clone
                    try {
                        Git.cloneRepository().setURI(theme.getSource())
                                .setDirectory(remote)
                                .setBranch("master")
                                .call()
                                .close();
                    }catch (Exception e) {
                        log.error("克隆仓库失败");
                        log.error(e.getMessage(), e);
                        throw new IllegalArgumentException("克隆仓库失败");
                    }
                }
                // 存在则pull
                try {
                    Git.open(remote).pull().call();
                }catch (Exception e) {
                    log.error("");
                    log.error(e.getMessage(), e);
                    throw new IllegalArgumentException("拉取仓库失败");
                }
            }else {
                // 不存在则clone
                try {
                    Git.cloneRepository().setURI(theme.getSource())
                            .setDirectory(remote)
                            .setBranch("master")
                            .call()
                            .close();
                }catch (Exception e) {
                    log.error("克隆仓库失败");
                    log.error(e.getMessage(), e);
                    throw new IllegalArgumentException("克隆仓库失败");
                }
            }
        }
        updateById(Theme.builder().id(id).syncTime(new Date()).build());
        return true;
    }
}




