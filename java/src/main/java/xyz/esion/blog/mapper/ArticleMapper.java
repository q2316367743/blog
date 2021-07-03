package xyz.esion.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.esion.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity xyz.esion.blog.entity.TArticle
 * @author Esion
 * @since 2021/6/21
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    void updateViewCountById(@Param("id") Integer id, @Param("viewCount") Integer viewCount);

}




