package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文章分类表
 * @TableName category
 * @author Esion
 * @since 2021/11/16
 */
@TableName(value ="category")
@Data
public class Category implements Serializable {
    /**
     * 分类ID
     */
    @TableId
    private Integer id;

    /**
     * 分类标题
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Boolean isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}