package xyz.esion.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.esion.blog.entity.Category;
import xyz.esion.blog.mapper.CategoryMapper;
import xyz.esion.blog.service.CategoryService;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Service("portalCategory")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
