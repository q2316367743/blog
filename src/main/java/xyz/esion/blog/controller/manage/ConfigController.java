package xyz.esion.blog.controller.manage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.domain.Config;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.ConfigService;

/**
 * @author Esion
 * @since 2021/11/25
 */
@RestController
@RequestMapping("manage/api/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigService configService;

    @GetMapping
    public Result<Config> info() {
        return Result.success(configService.info());
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody Config config) {
        configService.update(config);
        return Result.success(true);
    }

}
