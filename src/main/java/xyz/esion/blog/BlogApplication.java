package xyz.esion.blog;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ZipUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import xyz.esion.blog.constant.PathConstant;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Esion
 * @since 2021/6/20
 */
@SpringBootApplication
@MapperScan("xyz.esion.blog.mapper")
@EnableScheduling
public class BlogApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // 1. 创建相应文件夹
        FileUtil.mkdir(PathConstant.BACKUP_PATH);
        FileUtil.mkdir(PathConstant.IMAGE_PATH);
        FileUtil.mkdir(PathConstant.MUSIC_PATH);
        FileUtil.mkdir(PathConstant.RESOURCE_PATH);
        FileUtil.mkdir(PathConstant.THEME_PATH);
        File templatesPath = FileUtil.mkdir(PathConstant.TEMPLATES_PATH);
        File staticPath = FileUtil.mkdir(PathConstant.STATIC_PATH);
        // 2. 判断是否存在对应的模板文件
        if (Objects.requireNonNull(templatesPath.list()).length == 0) {
            ZipUtil.unzip(ResourceUtil.getStream("templates.zip"), templatesPath, StandardCharsets.UTF_8);
        }
        if (Objects.requireNonNull(staticPath.list()).length == 0) {
            ZipUtil.unzip(ResourceUtil.getStream("static.zip"), staticPath, StandardCharsets.UTF_8);
        }
    }
}
