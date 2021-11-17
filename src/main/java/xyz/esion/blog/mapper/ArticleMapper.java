package xyz.esion.blog.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.esion.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.esion.blog.global.KeyValue;

import java.util.List;

/**
 * @Entity xyz.esion.blog.entity.Article
 * @author Esion
 * @since 2021/11/16
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根据分类IDs查询分类数量
     *
     * @param categoryIds 分类IDs
     * @return 分类数量
     */
    List<KeyValue<Integer, Long>> countByCategory(@Param("categoryIds") List<Integer> categoryIds);

    /**
     * 根据ID，查询一个前端信息
     *
     * @param identification 唯一标识
     * @return 前端页面，没有原始内容
     */
    Article selectPortalByIdentification(String identification);

}




