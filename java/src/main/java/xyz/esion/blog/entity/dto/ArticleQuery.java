package xyz.esion.blog.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author qiao shengda
 * @since 2021/6/21
 */
public class ArticleQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分页页码
     * */
    private Integer page;

    /**
     * 分页页数
     * */
    private Integer size;

    /**
     * 文章标题
     * */
    private String title;

    /**
     * 文章分类
     * */
    private Integer categoryId;

    /**
     * 从小到大排序字段
     * */
    private List<Integer> orderByAsc;

    /**
     * 从大到小排序字段
     * */
    private List<Integer> orderByDesc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArticleQuery() {
        this.page = 1;
        this.size = 10;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getOrderByAsc() {
        return orderByAsc;
    }

    public void setOrderByAsc(List<Integer> orderByAsc) {
        this.orderByAsc = orderByAsc;
    }

    public List<Integer> getOrderByDesc() {
        return orderByDesc;
    }

    public void setOrderByDesc(List<Integer> orderByDesc) {
        this.orderByDesc = orderByDesc;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ArticleQueryDTO{" +
                "page=" + page +
                ", size=" + size +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                ", orderByAsc=" + orderByAsc +
                ", orderByDesc=" + orderByDesc +
                '}';
    }
}
