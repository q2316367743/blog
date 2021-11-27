package xyz.esion.blog.condition;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Esion
 * @since 2021/11/21
 */
@Data
public class ArticleCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 是否发布
     */
    private Integer status;

    /**
     * 正序
     */
    private List<String> orderBy;

    /**
     * 倒序
     */
    private List<String> orderByDesc;

}
