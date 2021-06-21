package xyz.esion.blog.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.global.Author;
import xyz.esion.blog.global.Config;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.GlobalService;

/**
 * 全局数据控制器
 *
 * @author qiao shengda
 * @since 2021/6/20
 */
@RestController
@RequestMapping("global")
public class GlobalController {

    private Author author;
    private Config config;
    private GlobalService globalService;

    @GetMapping("author")
    public Result author(){
        return Result.success().data("item", author);
    }

    @GetMapping("config")
    public Result config(){
        return Result.success().data("item", config);
    }

    @GetMapping("category")
    public Result category(){
        return Result.success().data("items", globalService.getCategory());
    }

    @Autowired
    public GlobalController(Author author, Config config, GlobalService globalService) {
        this.author = author;
        this.config = config;
        this.globalService = globalService;
    }

}
