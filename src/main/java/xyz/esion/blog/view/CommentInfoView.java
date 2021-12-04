package xyz.esion.blog.view;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Esion
 * @since 2021/12/4
 */
@Data
public class CommentInfoView implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Integer id;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否展示，0：未审查，1：通过，2：不通过
     */
    private Integer status;

    /**
     * 评论类型；1：访客
     */
    private Integer type;

    /**
     * 来源ID
     */
    private Integer sourceId;

    /**
     * 源标题
     */
    private String sourceTitle;

    /**
     * 所属评论，顶级为0
     */
    private Integer rootId;

    /**
     * 评论内容
     */
    private String content;

}
