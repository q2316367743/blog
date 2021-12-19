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
     * 图标
     */
    private String icon;

    /**
     * 描述
     */
    private String description;

    /**
     * 后台 - 有效
     */
    private Integer status;

    /**
     * 拒绝的原因
     * <p>后台 - 有效</p>
     */
    private String reason;

}
