package xyz.esion.blog.service;

import xyz.esion.blog.entity.CategoryList;

import java.util.List;

/**
 * @author Esion
 * @since 2021/6/21
 */
public interface CategoryService {

    /**
     * 获取全部分类
     *
     * @return 全部分类
     * */
    List<CategoryList> getCategory();

    /**
     * 根据分类ID获取分类信息
     *
     * @param id 分类ID
     * @return 分类信息
     * */
    CategoryList infoById(Integer id);

}
