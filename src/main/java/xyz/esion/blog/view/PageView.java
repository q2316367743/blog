package xyz.esion.blog.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 *
 * @author Esion
 * @since 2021/11/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageView<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Long pageNum;

    /**
     * 每页数量
     */
    private Long pageSize;

    /**
     * 页数
     */
    private Long count;

    /**
     * 总数
     */
    private Long total;

    /**
     * 记录
     */
    private List<T> records;

}
