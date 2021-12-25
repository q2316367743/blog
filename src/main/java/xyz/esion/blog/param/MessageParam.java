package xyz.esion.blog.param;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "类型不能为空")
    private Integer type;

    /**
     * 名称
     */
    @NotBlank(message = "昵称不能为空")
    private String name;

    /**
     * 电子邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    /**
     * 个人网站
     */
    private String url;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

}
