package xyz.esion.blog.module.manage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.domain.Website;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.WebsiteService;

/**
 * @author Esion
 * @since 2021/11/21
 */
@RestController
@RequestMapping("/manage/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final WebsiteService websiteService;

    @GetMapping("base")
    public Result<Website> base() {
        return Result.success(websiteService.info());
    }

}
