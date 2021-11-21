package xyz.esion.blog.view;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Esion
 * @since 2021/11/19
 */
@Data
public class PageInfoView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 页面标识
     */
    private String identification;

    /**
     * 页面标题
     */
    private String title;

    /**
     * 页面展示图片
     */
    private String image;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 阅读人数
     */
    private Integer viewCount;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 文章内容，内容为html文件
     */
    private String content;

}
