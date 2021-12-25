package xyz.esion.blog.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 网站数据
 *
 * @author Esion
 * @since 2021/11/16
 */
@Component
@Data
@RequiredArgsConstructor
public class Website implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章数量
     * */
    private Long articleCount;
    /**
     * 分类数量
     * */
    private Long categoryCount;

    /**
     * <p>运行时间</p>
     * <p>单位：天</p>
     * */
    private Integer runTime;

    /**
     * 总字数
     * */
    private Long wordCount;

    /**
     * 访问量
     * */
    private Integer accessCount;

    /**
     * 最后更新时间
     * */
    private Date lastUpdate;

}