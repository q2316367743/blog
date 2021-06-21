package xyz.esion.blog.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author qiao shengda
 * @since 2021/6/21
 */
public class ArticleQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page;

    private Integer size;

    private String title;

    private Integer categoryId;

    private List<String> orderByAsc;

    private List<String> orderByDesc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArticleQueryDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getOrderByAsc() {
        return orderByAsc;
    }

    public void setOrderByAsc(List<String> orderByAsc) {
        this.orderByAsc = orderByAsc;
    }

    public List<String> getOrderByDesc() {
        return orderByDesc;
    }

    public void setOrderByDesc(List<String> orderByDesc) {
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
