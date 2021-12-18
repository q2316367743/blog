package xyz.esion.blog.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.system.SystemUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import xyz.esion.blog.constant.PathConstant;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Esion
 * @since 2021/12/18
 */
@Configuration
public class ThemeConfig {

    private void base() {
        // 1. 创建相应文件夹
        FileUtil.mkdir(PathConstant.BACKUP_PATH);
        FileUtil.mkdir(PathConstant.IMAGE_PATH);
        FileUtil.mkdir(PathConstant.MUSIC_PATH);
        FileUtil.mkdir(PathConstant.RESOURCE_PATH);
        FileUtil.mkdir(PathConstant.THEMES_PATH);
    }

    @Profile("pro")
    @Bean
    public void pro() {
        base();
        File theme = FileUtil.mkdir(PathConstant.THEME_PATH);
        // 2. 判断是否存在对应的模板文件
        if (Objects.requireNonNull(theme.list()).length == 0) {
            ZipUtil.unzip(ResourceUtil.getStream("theme-default.zip"), theme, StandardCharsets.UTF_8);
        }
    }

    @Profile("theme")
    @Bean
    public void theme() {
        base();
        File target = new File(buildPath(
                SystemUtil.getUserInfo().getCurrentDir(),
                "src",
                "main",
                "resources"
        ));
        if (Objects.requireNonNull(target.list()).length == 0) {
            ZipUtil.unzip(ResourceUtil.getStream("theme-default.zip"), target, StandardCharsets.UTF_8);
        }
    }

    private String buildPath(String... paths) {
        return CollUtil.join(CollUtil.newLinkedList(paths), File.separator);
    }

}
