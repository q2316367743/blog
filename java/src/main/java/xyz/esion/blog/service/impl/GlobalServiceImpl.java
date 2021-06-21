package xyz.esion.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.esion.blog.entity.CategoryList;
import xyz.esion.blog.mapper.CategoryListMapper;
import xyz.esion.blog.service.GlobalService;

import java.util.List;

/**
 * @author qiao shengda
 * @since 2021/6/21
 */
@Service
public class GlobalServiceImpl implements GlobalService {

    private CategoryListMapper categoryMapper;

    @Override
    public List<CategoryList> getCategory() {
        return categoryMapper.selectList(new QueryWrapper<>());
    }

    @Autowired
    public GlobalServiceImpl(CategoryListMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
}
