package xyz.esion.blog.view;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/12/4
 */
@Data
public class DictView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String key;

    private String value;

}
