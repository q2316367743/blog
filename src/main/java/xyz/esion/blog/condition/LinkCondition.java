package xyz.esion.blog.condition;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/11/27
 */
@Data
public class LinkCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

}
