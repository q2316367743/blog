package xyz.esion.blog.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.esion.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.esion.blog.entity.dto.ArticleListBack;
import xyz.esion.blog.entity.dto.ArticleQuery;

/**
 * @Entity xyz.esion.blog.entity.TArticle
 * @author Esion
 * @since 2021/6/21
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 通过ID更新文章浏览量
     *
     * @param id 文章ID
     * @param viewCount 浏览量
     * */
    void updateViewCountById(@Param("id") Integer id, @Param("viewCount") Integer viewCount);

    /**
     * 查询全部的列表
     *
     * @param page 分页
     * @param query 查询参数
     * @return 结果
     * */
    Page<ArticleListBack> selectAllList(Page<ArticleListBack> page, ArticleQuery query);

}




