package xyz.esion.blog.controller.manage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.entity.dto.ArticleQuery;
import xyz.esion.blog.entity.view.ArticleListView;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.ArticleService;

/**
 * @author Esion
 * @since 2021/7/10
 */
@RestController("articleManage")
@RequestMapping("manage/article")
public class ArticleController {

    private ArticleService articleService;

    @GetMapping
    public Result page(ArticleQuery query){
        Page<ArticleListView> articles = articleService.pageBackByCondition(query);
        return Result.success().page(articles);
    }

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
}
