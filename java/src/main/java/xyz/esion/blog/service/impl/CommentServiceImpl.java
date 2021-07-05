package xyz.esion.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.esion.blog.entity.Comment;
import xyz.esion.blog.entity.dto.CommentListFront;
import xyz.esion.blog.entity.dto.CommentSave;
import xyz.esion.blog.mapper.CommentMapper;
import xyz.esion.blog.service.CommentService;

import java.util.*;
import java.util.stream.Collectors;

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
    public Integer save(CommentSave commentSave) {
        String[] ignore = new String[]{"commentId", "website"};
        if (BeanUtil.hasNullField(commentSave, ignore)){
            throw new IllegalArgumentException("新增评论错误，有参数为空");
        }
        Comment comment = BeanUtil.copyProperties(commentSave, Comment.class);
        if (ObjectUtil.isNotEmpty(commentSave.getCommentId())){
            // 回复评论
            Comment temp = commentMapper.selectById(commentSave.getCommentId());
            if (temp == null){
                throw new IllegalArgumentException("回复评论ID错误");
            }
            if (temp.getpId().equals(0)){
                // 回复一级评论
                comment.setpId(commentSave.getCommentId());
            }else {
                // 回复二级评论
                comment.setpId(temp.getpId());
            }
            comment.setArticleId(temp.getArticleId());
            comment.setTargetNickname(temp.getNickname());
            comment.setTargetWebsite(temp.getWebsite());
        }

        comment.setCreateTime(new Date());
        comment.setIsShow(0);
        comment.setType(1);
        return commentMapper.insert(comment);
    }

    @Override
    public List<CommentListFront> listByFront(Integer articleId) {
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>()
                .eq("article_id", articleId)
                .eq("is_show", 1));
        // 对评论进行处理
        Map<Integer, CommentListFront> oneComments = new HashMap<>();
        List<Comment> twoComments = new ArrayList<>();
        comments.forEach(item -> {
            if (item.getpId() == null || item.getpId().equals(0)){
                CommentListFront commentListFront = BeanUtil.copyProperties(item, CommentListFront.class);
                commentListFront.setChildren(new ArrayList<>());
                oneComments.put(item.getId(), commentListFront);
            }else {
                twoComments.add(item);
            }
        });
        twoComments.forEach(item -> {
            if (oneComments.containsKey(item.getpId())){
                oneComments.get(item.getpId()).getChildren().add(BeanUtil.copyProperties(item, CommentListFront.class));
            }
        });
        return oneComments
                .values()
                .stream()
                .sorted((a, b) -> (int) (a.getCreateTime().getTime() - b.getCreateTime().getTime()))
                .map(item -> {
                    if (item.getChildren().size() > 1){
                        item.getChildren().stream().sorted((a, b) -> (int) (a.getCreateTime().getTime() - b.getCreateTime().getTime()));
                    }
                    return item;
                })
                .collect(Collectors.toList());
    }
}
