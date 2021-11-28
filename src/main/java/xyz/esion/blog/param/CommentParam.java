package xyz.esion.blog.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/11/28
 */
@Data
public class CommentParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 个人网站
     */
    private String website;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 所属跟评论，顶级为0，属于那条评论的二级评论
     */
    private Integer rootId;

    /**
     * <p>@了谁</p>
     */
    private Integer targetId;

    /**
     * 评论内容
     */
    private String content;

}
