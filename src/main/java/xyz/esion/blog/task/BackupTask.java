package xyz.esion.blog.task;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.esion.blog.constant.BackupConstant;
import xyz.esion.blog.constant.PathConstant;
import xyz.esion.blog.service.AuthorService;
import xyz.esion.blog.service.ConfigService;
import xyz.esion.blog.service.WebsiteService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * 每隔时间将配置类定时备份任务
 *
 * @author Esion
 * @since 2021/11/20
 */
@Component
@RequiredArgsConstructor
public class BackupTask {

    private final AuthorService authorService;
    private final ConfigService configService;
    private final WebsiteService websiteService;

    @PostConstruct
    public void init() {
        // 备份之前，查询目录是否存在
        FileUtil.mkdir(PathConstant.BACKUP_PATH);
        authorTask();
        configTask();
    }

    /**
     * 作者缓存，每天备份一次
     */
    @Scheduled(cron = "0 0 0 */1 * *")
    public void authorTask() {
        String authorJson = JSONUtil.toJsonStr(authorService.info());
        FileUtil.writeString(authorJson, new File(BackupConstant.AUTHOR_PATH), StandardCharsets.UTF_8);
    }

    /**
     * 配置缓存，每天备份一次
     */
    @Scheduled(cron = "0 0 0 */1 * *")
    public void configTask() {
        String configJson = JSONUtil.toJsonStr(configService.info());
        FileUtil.writeString(configJson, new File(BackupConstant.CONFIG_PATH), StandardCharsets.UTF_8);
    }

}
