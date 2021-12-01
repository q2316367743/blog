package xyz.esion.blog.condition;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/11/27
 */
@Data
public class MessageCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private Boolean isRead;

}
