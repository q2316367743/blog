package xyz.esion.blog.task;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.esion.blog.service.WebsiteService;

import javax.annotation.PostConstruct;

/**
 * @author Esion
 * @since 2021/11/27
 */
@Component
@RequiredArgsConstructor
public class WebsiteTask {

    private final WebsiteService websiteService;

    @PostConstruct
    public void init() {
        // 初始化就同步一次
        sync();
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void sync() {
        websiteService.sync();
    }

}
