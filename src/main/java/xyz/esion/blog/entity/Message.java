package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 留言板
 * @TableName message
 */
@TableName(value ="message")
@Data
public class Message implements Serializable {
    /**
     * ID
     */
    @TableId
    private Integer id;

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