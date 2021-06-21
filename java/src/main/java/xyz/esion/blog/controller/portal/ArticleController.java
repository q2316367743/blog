package xyz.esion.blog.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.entity.dto.ArticleQueryDTO;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.ArticleService;

/**
 * 文章控制器
 *
 * @author Esion
 * @since 2021/6/20
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    private ArticleService articleService;

    @GetMapping
    public Result page(ArticleQueryDTO articleQueryDTO){
        return Result.success().page(articleService.pageByCondition(articleQueryDTO));
    }

    @GetMapping("{id}")
    public Result info(@PathVariable("id") Integer id){
        return Result.success().data("item", articleService.infoById(id));
    }

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
}
