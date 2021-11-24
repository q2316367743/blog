package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 页面
 * @TableName page
 * @author Esion
 * @since 2021/11/17
 */
@TableName(value ="page")
@Data
public class Page implements Serializable {

    /**
     * 页面ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 页面标题
     */
    private String title;

    /**
     * 页面展示图片
     */
    private String image;

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
     * 阅读人数
     */
    private Integer viewCount;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 文章内容，内容为html文件
     */
    private String content;

    /**
     * 文章原始内容，内容为Markdown文档
     */
    private String originalContent;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}