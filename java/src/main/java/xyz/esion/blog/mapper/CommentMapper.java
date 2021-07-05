package xyz.esion.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.esion.blog.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity xyz.esion.blog.entity.Comment
 * @author Esion
 * @since 2021/7/5
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




