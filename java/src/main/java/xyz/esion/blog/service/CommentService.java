package xyz.esion.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.esion.blog.entity.dto.CommentReq;
import xyz.esion.blog.entity.dto.CommentRsp;

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
     * @param commentReq 评论内容
     * @return 评论结果
     * */
    Integer save(CommentReq commentReq);

    /**
     * 根据文章ID分页查询评论
     *
     * @param page 页码
     * @param size 没有数
     * @param articleId 文章ID
     * @return 文章评论
     * */
    Page<CommentRsp> page(Integer page, Integer size, Integer articleId);

}
