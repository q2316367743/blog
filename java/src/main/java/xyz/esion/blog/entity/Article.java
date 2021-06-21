package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 * @TableName t_article
 * @author Esion
 * @since 2021/6/21
 */
@TableName(value ="t_article")
public class Article implements Serializable {
    /**
     * 文章ID
     */
    @TableId(type = IdType.AUTO)
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
     * 标签
     */
    private String tags;

    /**
     * 排序，默认更新时间截
     */
    private Long sequence;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Boolean isDelete;

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
     * 文章展示图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 文章展示图片
     */
    public void setImage(String image) {
        this.image = image;
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
     * 标签
     */
    public String getTags() {
        return tags;
    }

    /**
     * 标签
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 排序，默认更新时间截
     */
    public Long getSequence() {
        return sequence;
    }

    /**
     * 排序，默认更新时间截
     */
    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    /**
     * 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     */
    public void setDescription(String description) {
        this.description = description;
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
     * 逻辑删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 逻辑删除
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", categoryId=" + categoryId +
                ", tags='" + tags + '\'' +
                ", sequence=" + sequence +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", wordCount=" + wordCount +
                ", readTime=" + readTime +
                ", viewCount=" + viewCount +
                ", commentCount=" + commentCount +
                ", content='" + content + '\'' +
                '}';
    }
}