package xyz.esion.blog.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.esion.blog.entity.CategoryList;
import xyz.esion.blog.mapper.CategoryListMapper;

import java.util.List;

/**
 * 分类缓存
 *
 * @author Esion
 * @since 2021/7/10
 */
@Component
public class CategoryCache {

    private List<CategoryList> CACHE;

    private CategoryListMapper categoryListMapper;

    @Autowired
    public CategoryCache(CategoryListMapper categoryListMapper) {
        this.categoryListMapper = categoryListMapper;
        this.CACHE = categoryListMapper.selectList(new QueryWrapper<CategoryList>().orderByDesc("article_count"));
    }

    public synchronized void update(List<CategoryList> list){
        this.CACHE = list;
    }

    public List<CategoryList> get(){
        return this.CACHE;
    }

}
