package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 友情链接
 * @TableName link
 * @author Esion
 * @since 2021/11/27
 */
@TableName(value ="link")
@Data
public class Link implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean isDelete;

    /**
     * 图标
     */
    private String icon;

    /**
     * 名称
     */
    private String name;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 个人网站
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}