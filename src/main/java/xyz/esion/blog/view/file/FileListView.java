package xyz.esion.blog.view.file;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/12/8
 */
@Data
public class FileListView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 是否是目录
     */
    private Boolean isDirectory;

}
