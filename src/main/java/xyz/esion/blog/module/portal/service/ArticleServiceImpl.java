package xyz.esion.blog.module.portal.service;

import org.springframework.stereotype.Service;
import xyz.esion.blog.module.portal.view.ArticleListView;
import xyz.esion.blog.param.PageReqParam;
import xyz.esion.blog.view.PageRspView;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Override
    public PageRspView<ArticleListView> page(PageReqParam pageParam) {
        return null;
    }

}
