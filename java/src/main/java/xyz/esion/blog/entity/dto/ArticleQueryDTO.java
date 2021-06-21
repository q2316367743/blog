package xyz.esion.blog.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author qiao shengda
 * @since 2021/6/21
 */
public class ArticleQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer categoryId;

    private List<String> orderByAsc;

    private List<String> orderByDesc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ArticleQueryDTO{" +
                "name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", orderByAsc=" + orderByAsc +
                ", orderByDesc=" + orderByDesc +
                '}';
    }
}
