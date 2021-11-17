package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @TableId
    private Integer id;

    /**
     * 页面标识
     */
    private String identification;

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