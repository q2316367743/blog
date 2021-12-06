package xyz.esion.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.esion.blog.entity.Category;
import xyz.esion.blog.view.CategoryView;

import java.util.List;

/**
 * @author Esion
 * @since 2021/11/16
 */
public interface CategoryService extends IService<Category> {

    /**
     * 构建树形结构的分类<br />
     * 最多支持两级分类
     *
     * @return 树形的分类
     */
    List<CategoryView> tree();

}
