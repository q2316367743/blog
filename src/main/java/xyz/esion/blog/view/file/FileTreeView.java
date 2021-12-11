package xyz.esion.blog.view.file;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Esion
 * @since 2021/12/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FileTreeView extends FileListView {

    /**
     * 子集
     */
    private List<FileTreeView> children;

}
