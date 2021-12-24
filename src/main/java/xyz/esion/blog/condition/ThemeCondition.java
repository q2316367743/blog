package xyz.esion.blog.condition;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/12/24
 */
@Data
public class ThemeCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否启用
     */
    private Integer isEnable;

    /**
     * 主题类型
     */
    private Integer type;

    /**
     * 名称
     */
    private String name;

}
