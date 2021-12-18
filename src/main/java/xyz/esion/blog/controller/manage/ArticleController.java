package xyz.esion.blog.controller.manage;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.condition.ArticleCondition;
import xyz.esion.blog.entity.Article;
import xyz.esion.blog.entity.Category;
import xyz.esion.blog.enumeration.EditorTypeEnum;
import xyz.esion.blog.enumeration.ResultCodeEnum;
import xyz.esion.blog.global.NameConvertModel;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.param.ArticleParam;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.service.ArticleService;
import xyz.esion.blog.service.CategoryService;
import xyz.esion.blog.view.PageView;
import xyz.esion.blog.view.article.ArticleInfoView;
import xyz.esion.blog.view.article.ArticleListView;

import java.util.Arrays;

/**
 * @author Esion
 * @since 2021/11/21
 */
@RestController
@RequestMapping("manage/api/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

    @GetMapping
    public Result<PageView<ArticleListView>> page(
            @NameConvertModel PageParam pageParam,
            @NameConvertModel ArticleCondition articleCondition
    ) {
        return Result.success(articleService.page(pageParam, articleCondition));
    }

    @GetMapping("{id}")
    public Result<ArticleInfoView> info(@PathVariable Integer id) {
        Article article = articleService.getById(id);
        if (article == null) {
            return Result.fail(ResultCodeEnum.NOT_FOUND);
        }
        ArticleInfoView info = BeanUtil.copyProperties(article, ArticleInfoView.class);
        Category category = categoryService.getById(article.getCategoryId());
        if (category != null) {
            info.setCategoryName(category.getName());
        }
        info.setTags(Arrays.asList(article.getTags().split(",")));
        info.setIsTop(article.getSequence().equals(Long.MAX_VALUE));
        return Result.success(info);
    }

    @PostMapping
    public Result<Boolean> save(@RequestBody ArticleParam param) {
        Article article = convert(param);
        return Result.success(articleService.save(article));
    }

    @PutMapping("{id}")
    public Result<Boolean> update(@PathVariable Integer id, @RequestBody ArticleParam param) {
        Article article = convert(param);
        article.setId(id);
        return Result.success(articleService.updateById(article));
    }

    @DeleteMapping("{id}")
    public Result<Boolean> delete(@PathVariable Integer id) {
        return Result.success(articleService.removeById(id));
    }

    private Article convert(ArticleParam param) {
        Article article = new Article();
        article.setTitle(param.getTitle());
        article.setImage(param.getImage());
        article.setStatus(param.getStatus());
        article.setCategoryId(param.getCategoryId());
        if (param.getTags() != null) {
            article.setTags(CollUtil.join(param.getTags(), ","));
        }
        if (param.getIsTop() != null) {
            if (param.getIsTop()) {
                article.setSequence(Long.MAX_VALUE);
            }else {
                article.setSequence(System.currentTimeMillis());
            }
        }
        article.setDescription(param.getDescription());
        article.setIsDelete(false);
        article.setType(param.getType());
        article.setOriginalContent(param.getOriginalContent());
        if (param.getType() != null) {
            if (param.getType().equals(EditorTypeEnum.MARKDOWN.getValue())) {
                article.setContent(param.getContent());
            }else if (param.getType().equals(EditorTypeEnum.RICH_TEXT.getValue())) {
                article.setContent(param.getOriginalContent());
            }else if (param.getType().equals(EditorTypeEnum.HTML.getValue())){
                article.setContent(param.getOriginalContent());
            }else {
                throw new IllegalArgumentException("文章类型错误");
            }
        }
        return article;
    }

}
