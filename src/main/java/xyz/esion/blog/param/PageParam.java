package xyz.esion.blog.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Data
public class PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页面大小
     */
    private Integer pageSize;

    public PageParam() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    public PageParam(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
