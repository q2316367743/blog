package xyz.esion.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.esion.blog.entity.ArticleInfo;
import xyz.esion.blog.entity.ArticleList;
import xyz.esion.blog.entity.dto.ArticleQuery;

/**
 * 文章操作
 *
 * @author qiao shengda
 * @since 2021/6/21
 */
public interface ArticleService {

    /**
     * 根据条件分页查询文章列表
     *
     * @param condition 查询条件
     * @return 文章列表
     * */
    Page<ArticleList> pageByCondition(ArticleQuery condition);

    /**
     * 根据ID查询文章内容
     *
     * @param id 文章ID
     * @return 文章内容
     * */
    ArticleInfo infoById(Integer id);

}
