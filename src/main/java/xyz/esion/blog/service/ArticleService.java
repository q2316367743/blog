package xyz.esion.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.esion.blog.condition.ArticleCondition;
import xyz.esion.blog.entity.Article;
import xyz.esion.blog.global.KeyValue;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.view.PageView;
import xyz.esion.blog.view.article.ArticleListView;

import java.util.List;

/**
 * @author Esion
 * @since 2021/11/16
 */
public interface ArticleService extends IService<Article> {

    /**
     * 分页查询文章列表
     *
     * @param pageParam 分页参数
     * @param condition 查询条件
     * @return 文章列表
     */
    PageView<ArticleListView> page(PageParam pageParam, ArticleCondition condition);

    /**
     * 分页查询文章，
     * @param pageParam 分页条件
     * @param condition 查询条件
     * @return 文章列表
     */
    PageView<ArticleListView> search(PageParam pageParam, ArticleCondition condition);

    /**
     * 根据分类IDs查询分类数量
     *
     * @param categoryIds 分类IDs
     * @return 分类数量
     */
    List<KeyValue<Long, Long>> countByCategory(List<Integer> categoryIds);

}
