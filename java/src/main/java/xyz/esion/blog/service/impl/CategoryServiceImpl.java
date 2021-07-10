package xyz.esion.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.esion.blog.cache.CategoryCache;
import xyz.esion.blog.entity.CategoryList;
import xyz.esion.blog.mapper.CategoryListMapper;
import xyz.esion.blog.service.CategoryService;

import java.util.List;

/**
 * @author qiao shengda
 * @since 2021/6/21
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryListMapper categoryListMapper;

    private CategoryCache categoryCache;

    @Override
    public List<CategoryList> getCategoryTop() {
        return categoryCache.get().subList(0, 5);
    }

    @Override
    public List<CategoryList> getCategory() {
        return categoryCache.get();
    }

    @Override
    public CategoryList infoById(Integer id) {
        return categoryListMapper.selectById(id);
    }

    @Autowired
    public CategoryServiceImpl(CategoryListMapper categoryListMapper, CategoryCache categoryCache) {
        this.categoryListMapper = categoryListMapper;
        this.categoryCache = categoryCache;
    }
}
