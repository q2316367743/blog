package xyz.esion.blog.module.portal.view;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Data
public class ArticleListView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    private Integer id;

    /**
     * 文章标识
     */
    private String identification;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章展示图片
     */
    private String image;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String  categoryName;

    /**
     * 标签，使用英文逗号隔开
     */
    private List<String> tags;

    /**
     * 排序，默认更新时间截，正序，1：指定，时间戳：更新时间
     */
    private Long sequence;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 字数统计
     */
    private Integer wordCount;

    /**
     * 阅读时间，根据字数，单位分钟
     */
    private Integer readTime;

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
