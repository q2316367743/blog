package xyz.esion.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.esion.blog.condition.ArticleCondition;
import xyz.esion.blog.constant.GlobalConstant;
import xyz.esion.blog.entity.Article;
import xyz.esion.blog.entity.Category;
import xyz.esion.blog.enumeration.ArticleStatusEnum;
import xyz.esion.blog.global.KeyValue;
import xyz.esion.blog.mapper.ArticleMapper;
import xyz.esion.blog.mapper.CategoryMapper;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.service.ArticleService;
import xyz.esion.blog.view.PageView;
import xyz.esion.blog.view.article.ArticleListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final ArticleMapper articleMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public PageView<ArticleListView> page(PageParam pageParam, ArticleCondition condition) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        boolean titleExist = StrUtil.isNotBlank(condition.getTitle());
        boolean tagExist = StrUtil.isNotBlank(condition.getTag());
        queryWrapper.and(titleExist || tagExist, e -> {
            e.like(titleExist,
                            "title", condition.getTitle())
                    .or()
                    .like(tagExist,
                            "tags",
                            GlobalConstant.TAG_SEPARATOR + condition.getTag() + GlobalConstant.TAG_SEPARATOR);
        });
        queryWrapper.eq("status", condition.getStatus());
        queryWrapper.eq(condition.getCategoryId() != null, "category_id", condition.getCategoryId());
        if (condition.getOrderByDesc() != null) {
            queryWrapper.orderByDesc(CollUtil.isNotEmpty(condition.getOrderByDesc()),
                    ArrayUtil.toArray(condition.getOrderByDesc(), String.class));
        }
        if (condition.getOrderBy() != null) {
            queryWrapper.orderBy(CollUtil.isNotEmpty(condition.getOrderBy()),
                    true,
                    ArrayUtil.toArray(condition.getOrderBy(), String.class));
        }
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
                            view.setTags(CollUtil.removeAny(CollUtil.newLinkedList(item.getTags().split(",")), ""));
                            view.setIsTop(item.getSequence().equals(Long.MAX_VALUE));
                            return view;
                        })
                        .collect(Collectors.toList())
        );
    }

    @Override
    public PageView<ArticleListView> search(PageParam pageParam, ArticleCondition condition) {
        condition.setStatus(ArticleStatusEnum.RELEASE.getValue());
        condition.setTag(condition.getTitle());
        PageView<ArticleListView> page = this.page(pageParam, condition);
        page.getRecords().forEach(record -> {
            // 处理每一条记录
            // 处理标题
            record.setTitle(StrUtil.replace(record.getTitle(),
                    condition.getTitle(),
                    GlobalConstant.HIGH_LIGHT_START + condition.getTitle() + GlobalConstant.HIGH_LIGHT_END));
            // 处理标签
            record.setTags(record.getTags().stream().map(item -> {
                if (item.equals(condition.getTag())) {
                    return GlobalConstant.HIGH_LIGHT_START + item + GlobalConstant.HIGH_LIGHT_END;
                }
                return item;
            }).collect(Collectors.toList()));
        });
        return page;
    }

    @Override
    public List<KeyValue<Long, Long>> countByCategory(List<Integer> categoryIds) {
        return articleMapper.countByCategory(categoryIds);
    }

}
