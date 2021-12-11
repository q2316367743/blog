package xyz.esion.blog.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/12/11
 */
@Data
public class FileUpdateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 路径
     */
    private String path;

    /**
     * 内容
     */
    private String content;

}
