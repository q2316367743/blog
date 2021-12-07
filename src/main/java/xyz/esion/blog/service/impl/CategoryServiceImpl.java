package xyz.esion.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.esion.blog.entity.Category;
import xyz.esion.blog.mapper.CategoryMapper;
import xyz.esion.blog.service.CategoryService;
import xyz.esion.blog.view.CategoryView;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<CategoryView> tree() {
        List<CategoryView> views = new LinkedList<>();
        List<Category> categories = list();
        // 构建一级分类
        for (Category category : categories) {
            if (category.getPId().equals(0)) {
                CategoryView view = new CategoryView();
                view.setId(category.getId());
                view.setName(category.getName());
                view.setCount(0L);
                view.setArticles(new LinkedList<>());
                view.setChildren(new LinkedList<>());
                views.add(view);
            }
        }
        // 构建二级分类
        for (Category category : categories) {
            if (!category.getPId().equals(0)) {
                for (CategoryView view : views) {
                    if (view.getId().equals(category.getPId())) {
                        CategoryView child = new CategoryView();
                        child.setArticles(new LinkedList<>());
                        child.setId(category.getId());
                        child.setName(category.getName());
                        child.setCount(0L);
                        view.getChildren().add(child);
                        break;
                    }
                }
            }
        }
        return views;
    }

}
