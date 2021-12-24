package xyz.esion.blog.service.impl;

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
import java.util.Date;
import java.util.Objects;

/**
 * @author Esion
 * @since 2021/12/24
 */
@Service
@Slf4j
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme>
    implements ThemeService{

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
        File local = null;
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
            FileUtil.copy(staticFolder, new File(PathConstant.STATIC_PATH), true);
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
            File remote = new File(theme.getSource(), theme.getName());
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
                                .call();
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
                            .call();
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




