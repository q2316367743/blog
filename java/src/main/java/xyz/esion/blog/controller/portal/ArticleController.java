package xyz.esion.blog.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.entity.CategoryList;
import xyz.esion.blog.entity.dto.ArticleQueryDTO;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.ArticleService;
import xyz.esion.blog.service.CategoryService;

/**
 * 文章控制器
 *
 * @author Esion
 * @since 2021/6/20
 */
@RestController
@RequestMapping("api/article")
public class ArticleController {

    private ArticleService articleService;
    private CategoryService categoryService;

    @GetMapping("all")
    public Result page(ArticleQueryDTO articleQueryDTO){
        return Result.success().page(articleService.pageByCondition(articleQueryDTO));
    }

    @GetMapping("info/{id}")
    public Result info(@PathVariable("id") Integer id){
        return Result.success().data("item", articleService.infoById(id));
    }

    @GetMapping("category/{categoryId}")
    public Result category(
            @PathVariable("categoryId") Integer categoryId,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
        CategoryList categoryList = categoryService.infoById(categoryId);
        if (categoryList == null){
            return Result.fail().message("分类ID错误");
        }
        return Result.success().page(articleService.pageByCategory(page, size, categoryId)).data("item", categoryList);
    }

    @Autowired
    public ArticleController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }
}
