package xyz.esion.blog.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/12/9
 */
@Data
public class FileParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件夹所在目录
     */
    private String path;

    /**
     * 文件夹名称
     */
    private String name;

}
