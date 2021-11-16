package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName comment
 * @author Esion
 * @since 2021/11/16
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 是否展示，0：未审查，1：通过，2：不通过
     */
    private Boolean isShow;

    /**
     * 浏览器版本
     */
    private String browser;

    /**
     * 系统
     */
    private String systemVersion;

    /**
     * 评论类型；1：访客
     */
    private Byte type;

    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 所在评论的ID
     */
    private Integer pId;

    /**
     * 回复对象ID，冗余
     */
    private Integer targetId;

    /**
     * 回复对象昵称，冗余
     */
    private String targetNickname;

    /**
     * 回复对象个人网站，冗余
     */
    private String targetWebsite;

    /**
     * 评论内容
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}