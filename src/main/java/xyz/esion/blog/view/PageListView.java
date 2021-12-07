package xyz.esion.blog.view;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Esion
 * @since 2021/12/7
 */
@Data
public class PageListView implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 是否发布
     */
    private Integer status;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /**
     * 阅读人数
     */
    private Integer viewCount;

    /**
     * 评论数量
     */
    private Integer commentCount;

}
