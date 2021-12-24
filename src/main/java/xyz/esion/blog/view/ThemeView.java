package xyz.esion.blog.view;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Esion
 * @since 2021/12/24
 */
@Data
public class ThemeView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

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
     * 同步时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date syncTime;

    /**
     * 启用时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enableTime;

    /**
     * 是否启用
     */
    private Integer isEnable;

    /**
     * 主题类型
     */
    private Integer type;

    /**
     * 来源
     */
    private String source;

    /**
     * 名称
     */
    private String name;

}
