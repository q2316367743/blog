package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 留言板
 * @TableName message
 * @author Esion
 * @since 2021/11/16
 */
@TableName(value ="message")
@Data
public class Message implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 逻辑删除
     */
    private Boolean isDelete;

    /**
     * 类型
     */
    private Byte type;

    /**
     * 名称
     */
    private String name;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 内容
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}