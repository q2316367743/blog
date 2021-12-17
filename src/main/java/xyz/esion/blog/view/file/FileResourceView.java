package xyz.esion.blog.view.file;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Esion
 * @since 2021/12/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FileResourceView extends FileListView {

    /**
     * 链接，只有文件有
     */
    private String link;

}
