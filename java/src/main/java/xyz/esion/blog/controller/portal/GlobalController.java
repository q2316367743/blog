package xyz.esion.blog.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.global.Author;
import xyz.esion.blog.global.Config;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.global.Website;
import xyz.esion.blog.service.AuthorService;
import xyz.esion.blog.service.CategoryService;
import xyz.esion.blog.service.ConfigService;
import xyz.esion.blog.service.NoticeService;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局数据控制器
 *
 * @author qiao shengda
 * @since 2021/6/20
 */
@RestController
@RequestMapping("api/global")
public class GlobalController {

    private AuthorService authorService;
    private ConfigService configService;
    private CategoryService categoryService;
    private NoticeService noticeService;
    private Website website;

    @GetMapping("author")
    public Result author(){
        return Result.success().data("item", authorService.getBase());
    }

    @GetMapping("about")
    public Result about(){
        return Result.success().data("item", authorService.getOther());
    }

    @GetMapping("config")
    public Result config(){
        return Result.success().data("item", configService.get());
    }

    @GetMapping("category/top")
    public Result categoryTop(){
        return Result.success().data("items", categoryService.getCategoryTop());
    }

    @GetMapping("category/list")
    public Result categoryList(){
        return Result.success().data("items", categoryService.getCategory());
    }

    @GetMapping("web_info")
    public Result notice(){
        return Result.success().data("items", noticeService.list()).data("item", website);
    }

    public GlobalController(AuthorService authorService, ConfigService configService, CategoryService categoryService, NoticeService noticeService, Website website) {
        this.authorService = authorService;
        this.configService = configService;
        this.categoryService = categoryService;
        this.noticeService = noticeService;
        this.website = website;
    }

}
