package xyz.esion.blog.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/12/24
 */
@Data
public class ThemeParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主题类型{@link xyz.esion.blog.enumeration.ThemeTypeEnum}
     * <br />
     * 注意：<br />
     * 如果是远程，则项目存放在<strong>/主题文件夹/名称</strong><br />
     * 如果是本地，则在本地目录
     */
    @NotNull(message = "主题类型不能为空")
    private Integer type;

    /**
     * 来源，远程地址/本地地址
     */
    @NotBlank(message = "来源不能为空")
    private String source;

    /**
     * 名称
     */
    @NotBlank(message = "主题名称不能为空")
    @Length(max = 40, message = "主题名称不能大于40个字符")
    private String name;

}
