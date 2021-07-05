package xyz.esion.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.esion.blog.entity.Comment;
import xyz.esion.blog.entity.dto.CommentReq;
import xyz.esion.blog.entity.dto.CommentRsp;
import xyz.esion.blog.mapper.CommentMapper;
import xyz.esion.blog.service.CommentService;

import java.util.Date;

/**
 * @author Esion
 * @since 2021/7/4
 */
@Service
public class CommentServiceImpl implements CommentService {

    private CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public Integer save(CommentReq commentReq) {
        if (BeanUtil.hasNullField(commentReq, "commentId", "website")){
            throw new IllegalArgumentException("新增评论错误，有参数为空");
        }
        Comment comment = BeanUtil.copyProperties(commentReq, Comment.class);
        if (ObjectUtil.isNotEmpty(commentReq.getCommentId())){
            Comment temp = commentMapper.selectById(commentReq.getCommentId());
            if (temp == null){
                throw new IllegalArgumentException("回复评论ID错误");
            }
            comment.setArticleId(temp.getArticleId());
            comment.setpId(commentReq.getCommentId());
            comment.setTargetNickname(temp.getNickname());
            comment.setTargetWebsite(temp.getWebsite());
        }

        comment.setCreateTime(new Date());
        comment.setIsShow(0);
        comment.setType(1);
        return commentMapper.insert(comment);
    }

    @Override
    public Page<CommentRsp> page(Integer page, Integer size, Integer articleId) {
        return null;
    }
}
