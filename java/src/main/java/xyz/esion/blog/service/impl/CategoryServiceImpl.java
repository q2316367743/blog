package xyz.esion.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    private CategoryListMapper categoryMapper;

    @Override
    public List<CategoryList> getCategoryTop() {
        return categoryMapper.selectList(new QueryWrapper<CategoryList>().orderByDesc("article_count").last("limit 5"));
    }

    @Override
    public List<CategoryList> getCategory() {
        return categoryMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public CategoryList infoById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Autowired
    public CategoryServiceImpl(CategoryListMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
}
