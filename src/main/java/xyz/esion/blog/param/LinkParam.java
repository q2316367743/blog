package xyz.esion.blog.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/11/27
 */
@Data
public class LinkParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 图标
     */
    private String icon;

    /**
     * 名称
     */
    private String name;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 个人网站
     */
    private String url;

    /**
     * 描述
     */
    private String description;

}
