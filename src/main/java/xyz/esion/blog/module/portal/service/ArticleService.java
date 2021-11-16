package xyz.esion.blog.module.portal.service;

import xyz.esion.blog.module.portal.view.ArticleListView;
import xyz.esion.blog.param.PageReqParam;
import xyz.esion.blog.view.PageRspView;

/**
 * @author Esion
 * @since 2021/11/16
 */
public interface ArticleService {

    /**
     * 分页查询文章列表
     *
     * @param pageParam 分页参数
     * @return 文章列表
     */
    PageRspView<ArticleListView> page(PageReqParam pageParam);

}
