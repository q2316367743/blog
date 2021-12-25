package xyz.esion.blog.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/11/28
 */
@Data
public class CommentParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 电子邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 个人网站
     */
    private String website;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 来源ID
     */
    @NotNull(message = "来源ID不能为空")
    private Integer sourceId;

    /**
     * 来源类型
     */
    @NotNull(message = "来源类型不能为空")
    private Integer sourceType;

    /**
     * 所属跟评论，顶级为0，属于那条评论的二级评论
     */
    private Integer rootId;

    /**
     * <p>@了谁</p>
     */
    private Integer targetId;

    /**
     * 评论内容
     */
    private String content;

}
