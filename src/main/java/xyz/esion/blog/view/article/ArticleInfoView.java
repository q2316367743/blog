package xyz.esion.blog.view.article;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Esion
 * @since 2021/11/17
 */
@Data
public class ArticleInfoView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    private Integer id;

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
     * 分类ID
     */
    private String categoryName;

    /**
     * 标签，使用英文逗号隔开
     */
    private List<String> tags;

    /**
     * 排序，默认更新时间截，倒序，1：置顶，0：更新时间
     */
    private Boolean isTop;

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
     * 文章内容编写类型
     */
    private Integer type;

    /**
     * 文章内容，内容为html文件
     */
    private String content;

    /**
     * 文章原始内容，内容为Markdown文档
     */
    private String originalContent;

}
