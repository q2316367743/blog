package xyz.esion.blog.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.global.Author;
import xyz.esion.blog.global.Config;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.global.Website;
import xyz.esion.blog.service.CategoryService;
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

    private Author author;
    private Config config;
    private CategoryService categoryService;
    private NoticeService noticeService;
    private Website website;

    @GetMapping("author")
    public Result author(){
        Map<String, Object> item = new HashMap<>(5);
        item.put("name", author.getName());
        item.put("description", author.getDescription());
        item.put("avatar", author.getAvatar());
        item.put("gitee", author.getGitee());
        item.put("other", author.getOther());
        return Result.success().data("item", item);
    }

    @GetMapping("about")
    public Result about(){
        Map<String, Object> item = new HashMap<>(5);
        item.put("name", author.getName());
        item.put("base_info", author.getBaseInfo());
        return Result.success().data("item", item);
    }

    @GetMapping("config")
    public Result config(){
        return Result.success().data("item", config);
    }

    @GetMapping("category/top")
    public Result categoryTop(){
        return Result.success().data("items", categoryService.getCategory());
    }

    @GetMapping("category/list")
    public Result categoryList(){
        return Result.success().data("items", categoryService.getCategory());
    }

    @GetMapping("web_info")
    public Result notice(){
        return Result.success().data("items", noticeService.list()).data("item", website);
    }

    @Autowired
    public GlobalController(Author author, Config config, CategoryService categoryService, NoticeService noticeService, Website website) {
        this.author = author;
        this.config = config;
        this.categoryService = categoryService;
        this.noticeService = noticeService;
        this.website = website;
    }
}
