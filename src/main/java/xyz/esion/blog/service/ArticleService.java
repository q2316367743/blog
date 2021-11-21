package xyz.esion.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.esion.blog.entity.Article;
import xyz.esion.blog.global.KeyValue;
import xyz.esion.blog.view.ArticleListView;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.view.PageView;

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
     * @param queryWrapper 查询条件
     * @return 文章列表
     */
    PageView<ArticleListView> page(PageParam pageParam, QueryWrapper<Article> queryWrapper);

    /**
     * 根据分类IDs查询分类数量
     *
     * @param categoryIds 分类IDs
     * @return 分类数量
     */
    List<KeyValue<Integer, Long>> countByCategory(List<Integer> categoryIds);

    /**
     * 根据identification查询信息
     *
     * @param identification 唯一标识
     * @return 文章信息
     */
    Article info(String identification);

}
