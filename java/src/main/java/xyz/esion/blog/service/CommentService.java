package xyz.esion.blog.service;

import xyz.esion.blog.entity.Comment;
import xyz.esion.blog.entity.dto.CommentListFront;
import xyz.esion.blog.entity.dto.CommentSave;

import java.util.List;

/**
 * 评论接口
 *
 * @author Esion
 * @since 2021/7/4
 */
public interface CommentService {

    /**
     * 新增评论
     *
     * @param commentSave 评论内容
     * @return 评论结果
     * */
    Integer save(CommentSave commentSave);

    /**
     * 根据文章ID分页查询评论
     *
     * @param articleId 文章ID
     * @return 文章评论
     * */
    List<CommentListFront> listByFront(Integer articleId);

}
