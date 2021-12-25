package xyz.esion.blog.controller.manage;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.esion.blog.condition.ThemeCondition;
import xyz.esion.blog.constant.PathConstant;
import xyz.esion.blog.entity.Theme;
import xyz.esion.blog.enumeration.ThemeTypeEnum;
import xyz.esion.blog.global.NameConvertModel;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.param.ThemeParam;
import xyz.esion.blog.service.FileService;
import xyz.esion.blog.service.ThemeService;
import xyz.esion.blog.view.PageView;
import xyz.esion.blog.view.ThemeView;
import xyz.esion.blog.view.file.FileTreeView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 主题控制器
 *
 * @author Esion
 * @since 2021/12/24
 */
@RestController
@RequestMapping("manage/api/theme")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;
    private final FileService fileService;

    @GetMapping
    public Result<PageView<ThemeView>> page(
            @NameConvertModel PageParam pageParam,
            @NameConvertModel ThemeCondition condition
    ) {
        Page<Theme> themePage = themeService.page(
                new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                new QueryWrapper<Theme>()
                        .like(StrUtil.isNotBlank(condition.getName()), "name", condition.getName())
                        .eq(condition.getType() != null, "type", condition.getType())
                        .eq(condition.getIsEnable() != null, "is_enable", condition.getIsEnable()));
        return Result.success(new PageView<>(
                themePage.getCurrent(),
                themePage.getSize(),
                themePage.getPages(),
                themePage.getTotal(),
                themePage.getRecords().stream().map(item -> BeanUtil.copyProperties(item, ThemeView.class)).collect(Collectors.toList())
        ));
    }

    @PostMapping("save")
    public Result<Boolean> save(@Validated @RequestBody ThemeParam param) {
        return Result.success(themeService.save(BeanUtil.copyProperties(param, Theme.class)));
    }

    @PutMapping("{id}/update")
    public Result<Boolean> update(@PathVariable Integer id, @RequestBody ThemeParam param) {
        Theme theme = BeanUtil.copyProperties(param, Theme.class);
        theme.setId(id);
        return Result.success(themeService.updateById(theme));
    }

    @DeleteMapping("{id}")
    public Result<Boolean> remove(@PathVariable Integer id) {
        return Result.success(themeService.removeById(id));
    }

    /*------------------------------------------------------操作------------------------------------------------------*/

    @PutMapping("{id}/enable")
    public Result<Boolean> enable(@PathVariable Integer id) {
        return Result.success(themeService.enable(id));
    }

    @PutMapping("{id}/sync")
    public Result<Boolean> sync(@PathVariable Integer id) {
        return Result.success(themeService.sync(id));
    }

    @PostMapping("upload")
    public Result<Boolean> upload(MultipartFile multipartFil) {
        return Result.success();
    }

    @GetMapping("{id}/download")
    public Result<Boolean> download(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Theme theme = themeService.getById(id);
        if (theme.getType().equals(ThemeTypeEnum.LOCAL.getValue())) {
            fileService.download(theme.getSource(), response);
        }else if (theme.getType().equals(ThemeTypeEnum.REMOTE.getValue())){
            fileService.download(PathConstant.THEMES_PATH + File.separator + theme.getName(), response);
        }else {
            return Result.fail("主题类型错误");
        }
        return Result.success();
    }

    @GetMapping("{id}/ls")
    public Result<List<FileTreeView>> ls(@PathVariable Integer id) {
        return Result.success(null);
    }

}
