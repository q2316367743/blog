package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 文章分类表
 * @TableName t_category
 * @author Esion
 * @since 2021/6/21
 */
@TableName(value ="t_category")
public class Category implements Serializable {
    /**
     * 分类ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分类标题
     */
    private String name;

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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}