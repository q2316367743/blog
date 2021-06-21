package xyz.esion.blog.service;

import xyz.esion.blog.entity.CategoryList;

import java.util.List;

/**
 * @author Esion
 * @since 2021/6/21
 */
public interface GlobalService {

    /**
     * 获取全部分类
     *
     * @return 全部分类
     * */
    List<CategoryList> getCategory();

}
