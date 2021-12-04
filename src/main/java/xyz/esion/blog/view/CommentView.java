package xyz.esion.blog.view;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Esion
 * @since 2021/11/28
 */
@Data
public class CommentView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 图标
     */
    private String icon;

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
    private Integer type;

    /**
     * 所属跟评论，顶级为0
     */
    private Integer rootId;

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

    /**
     * 子评论
     */
    private List<CommentView> children;

}
