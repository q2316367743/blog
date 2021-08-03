package xyz.esion.blog.controller.manage;

import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.global.Config;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.ConfigService;

/**
 * @author Esion
 * @since 2021/7/12
 */
@RestController
@RequestMapping("manage/config")
public class ConfigController {

    private ConfigService configService;

    @GetMapping
    public Result info(){
        return Result.success().item(configService.get());
    }

    @PutMapping
    public Result update(@RequestBody Config config){
        return Result.choose(configService.update(config));
    }

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }
}
