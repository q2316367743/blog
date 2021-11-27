package xyz.esion.blog.view;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 文章 -> 分类列表
 *
 * @author Esion
 * @since 2021/11/27
 */
@Data
public class ArticleCategoryListView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 更新时间 -> 年
     */
    private String year;

    private List<Item> items;

    @Data
    public static class Item implements Serializable {

        /**
         * 文章ID
         */
        private Integer id;

        /**
         * 文章标题
         */
        private String title;

        /**
         * 更新时间 -> 年
         */
        private String year;

        /**
         * 更新时间 -> 月
         */
        private String month;

        /**
         * 更新时间 -> 日
         */
        private String day;

    }

}
