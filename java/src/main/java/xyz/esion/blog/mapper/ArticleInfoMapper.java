package xyz.esion.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.esion.blog.entity.ArticleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity xyz.esion.blog.entity.ArticleInfo
 * @author Esion
 * @since  2021/6/21
 */
@Mapper
public interface ArticleInfoMapper extends BaseMapper<ArticleInfo> {

    /**
     * 获取全部文章总字数
     *
     * @return 总字数
     * */
    Long selectSumWithWordCount();

}




