package xyz.esion.blog.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Data
public class CategoryView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    private Integer id;

    /**
     * 分类标题
     */
    private String name;

    /**
     * 数量
     */
    private Long count;

    /**
     * 前十篇文章
     */
    private List<Article> articles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Article implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer id;

        private String title;

    }


}
