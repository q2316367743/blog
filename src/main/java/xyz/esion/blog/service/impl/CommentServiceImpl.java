package xyz.esion.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.esion.blog.entity.Comment;
import xyz.esion.blog.service.CommentService;
import xyz.esion.blog.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




