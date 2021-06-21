package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName v_article_list
 * @author Esion
 * @since 2021/6/21
 */
@TableName(value ="v_article_list")
public class ArticleList implements Serializable {
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
     * 分类标题
     */
    private String categoryName;

    /**
     * 标签
     */
    private String tags;

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

    @Override
    public String toString() {
        return "ArticleList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", tags='" + tags + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}