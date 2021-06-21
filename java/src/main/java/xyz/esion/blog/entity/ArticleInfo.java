package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName v_article_info
 * @author Esion
 * @since  2021/6/21
 */
@TableName(value ="v_article_info")
public class ArticleInfo implements Serializable {
    /**
     * 文章ID
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 分类标题
     */
    private String categoryName;

    /**
     * 标签，使用英文逗号隔开
     */
    private String tags;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 字数统计
     */
    private Integer wordCount;

    /**
     * 阅读时间，根据字数
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
     * 文章内容，内容为Markdown文档
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 文章ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 分类ID
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 分类ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 分类标题
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 分类标题
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 标签，使用英文逗号隔开
     */
    public String getTags() {
        return tags;
    }

    /**
     * 标签，使用英文逗号隔开
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 字数统计
     */
    public Integer getWordCount() {
        return wordCount;
    }

    /**
     * 字数统计
     */
    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    /**
     * 阅读时间，根据字数
     */
    public Integer getReadTime() {
        return readTime;
    }

    /**
     * 阅读时间，根据字数
     */
    public void setReadTime(Integer readTime) {
        this.readTime = readTime;
    }

    /**
     * 阅读人数
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 阅读人数
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 评论数量
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 评论数量
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 文章内容，内容为Markdown文档
     */
    public String getContent() {
        return content;
    }

    /**
     * 文章内容，内容为Markdown文档
     */
    public void setContent(String content) {
        this.content = content;
    }
}