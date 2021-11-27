package xyz.esion.blog.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/11/26
 */
@Data
public class MessageParam implements Serializable {

    /**
     * ID
     */
    private Integer id;

    /**
     * 类型
     */
    private Integer type;

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
     * 内容
     */
    private String content;

}
