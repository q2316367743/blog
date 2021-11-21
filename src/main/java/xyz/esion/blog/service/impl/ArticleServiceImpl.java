package xyz.esion.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.esion.blog.entity.Article;
import xyz.esion.blog.entity.Category;
import xyz.esion.blog.global.KeyValue;
import xyz.esion.blog.mapper.ArticleMapper;
import xyz.esion.blog.mapper.CategoryMapper;
import xyz.esion.blog.service.ArticleService;
import xyz.esion.blog.view.ArticleListView;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.view.PageView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Service("portalArticle")
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final ArticleMapper articleMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public PageView<ArticleListView> page(PageParam pageParam, QueryWrapper<Article> queryWrapper) {
        Page<Article> page = articleMapper.selectPage(
                new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                queryWrapper.orderByDesc("sequence"));
        List<Article> records = page.getRecords();
        // 获取全部分类
        List<Integer> categoryIds = records.stream().map(Article::getCategoryId).collect(Collectors.toList());
        Map<Integer, Category> categoryMap = new HashMap<>(categoryIds.size());
        if (!categoryIds.isEmpty()) {
            categoryMap.putAll(categoryMapper
                    .selectList(new QueryWrapper<Category>().in("id", categoryIds))
                    .stream().collect(Collectors.toMap(Category::getId, e -> e)));
        }
        return new PageView<>(
                page.getCurrent(),
                page.getSize(),
                page.getPages(),
                page.getTotal(),
                records.stream()
                        .map(item -> {
                            ArticleListView view = BeanUtil.copyProperties(item, ArticleListView.class);
                            if (categoryMap.containsKey(item.getCategoryId())) {
                                view.setCategoryName(categoryMap.get(item.getCategoryId()).getName());
                            }
                            view.setTags(Arrays.asList(item.getTags().split(",")));
                            return view;
                        })
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<KeyValue<Integer, Long>> countByCategory(List<Integer> categoryIds) {
        return articleMapper.countByCategory(categoryIds);
    }

    @Override
    public Article info(String identification) {
        return articleMapper.selectPortalByIdentification(identification);
    }

}
