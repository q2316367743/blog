package xyz.esion.blog.view;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 *
 * @author Esion
 * @since 2021/11/16
 */
@Data
public class PageRspView<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 页数
     */
    private Integer count;

    /**
     * 总数
     */
    private Integer total;

    /**
     * 记录
     */
    private List<T> records;

}
