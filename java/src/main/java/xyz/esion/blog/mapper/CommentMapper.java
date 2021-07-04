package xyz.esion.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.esion.blog.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author Esion
 * @Entity xyz.esion.blog.entity.Comment
 * @since 2021/7/4
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




