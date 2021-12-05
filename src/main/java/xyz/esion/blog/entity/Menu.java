package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 菜单
 *
 * @author Esion
 * @TableName menu
 * @since 2021/11/17
 */
@TableName(value = "menu")
@Data
public class Menu implements Serializable {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 图标
     */
    private String icon;

    /**
     * 名称
     */
    private String name;

    /**
     * 打开方式：0：当前页面，1：新页面
     */
    private Integer target;

    /**
     * 链接
     */
    private String href;

    /**
     * 父级菜单，顶级为0
     */
    private Integer pId;

    /**
     * 顺序
     */
    private Integer sequence;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @JsonIgnore
    private Boolean isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}