package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 通知
 * @TableName notice
 */
@TableName(value ="notice")
@Data
public class Notice implements Serializable {
    /**
     * ID
     */
    @TableId
    private Integer id;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容，html字符串
     */
    private String content;

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