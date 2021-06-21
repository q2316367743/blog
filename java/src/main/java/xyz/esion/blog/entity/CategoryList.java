package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName v_category_list
 * @author Esion
 * @since 2021/6/21
 */
@TableName(value ="v_category_list")
public class CategoryList implements Serializable {
    /**
     * 分类ID
     */
    private Integer id;

    /**
     * 分类标题
     */
    private String name;

    /**
     * 文章数量
     * */
    private Integer articleCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 分类ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 分类标题
     */
    public String getName() {
        return name;
    }

    /**
     * 分类标题
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 文章数量
     * */
    public Integer getArticleCount() {
        return articleCount;
    }

    /**
     * 文章数量
     * */
    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

}