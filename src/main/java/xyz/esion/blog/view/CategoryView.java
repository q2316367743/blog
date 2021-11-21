package xyz.esion.blog.view;

import lombok.Data;

import java.io.Serializable;

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

}
