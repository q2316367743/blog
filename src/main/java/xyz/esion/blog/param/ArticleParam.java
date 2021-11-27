package xyz.esion.blog.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Esion
 * @since 2021/11/21
 */
@Data
public class ArticleParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章展示图片
     */
    private String image;

    /**
     * 是否发布
     */
    private Integer status;

    /**
     * 分类ID
     */
    private Integer categoryId;

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
     * 文章内容编写类型
     */
    private Integer type;

    /**
     * HTML文件
     */
    private String content;

    /**
     * 文章原始内容，内容为Markdown文档
     */
    private String originalContent;

}
