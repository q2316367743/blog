package xyz.esion.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.esion.blog.cache.CategoryCache;
import xyz.esion.blog.entity.ArticleInfo;
import xyz.esion.blog.entity.ArticleList;
import xyz.esion.blog.entity.CategoryList;
import xyz.esion.blog.entity.dto.ArticleListBack;
import xyz.esion.blog.entity.dto.ArticleQuery;
import xyz.esion.blog.entity.view.ArticleListView;
import xyz.esion.blog.enumerate.ArticleOrderFieldEnum;
import xyz.esion.blog.enumerate.ArticleOrderTypeEnum;
import xyz.esion.blog.mapper.ArticleListMapper;
import xyz.esion.blog.mapper.ArticleMapper;
import xyz.esion.blog.mapper.ArticleInfoMapper;
import xyz.esion.blog.service.ArticleService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author qiao shengda
 * @since 2021/6/21
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleMapper articleMapper;
    private ArticleListMapper articleListMapper;
    private ArticleInfoMapper articleInfoMapper;
    private CategoryCache categoryCache;

    @Override
    public Page<ArticleList> pageByCondition(ArticleQuery condition) {
        QueryWrapper<ArticleList> wrapper = new QueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(condition.getTitle()), "title", condition.getTitle());
        wrapper.eq(ObjectUtil.isNotEmpty(condition.getCategoryId()), "category_id", condition.getCategoryId());
        if (ObjectUtil.isNotNull(condition.getOrderByAsc()) && !condition.getOrderByAsc().isEmpty()){
            for (Integer id : condition.getOrderByAsc()){
                String field = ArticleOrderFieldEnum.getFieldById(id);
                wrapper.orderByAsc(ObjectUtil.isNotEmpty(field), field);
            }
        }
        if (ObjectUtil.isNotNull(condition.getOrderByDesc()) && !condition.getOrderByDesc().isEmpty()){
            for (Integer id : condition.getOrderByDesc()){
                String field = ArticleOrderFieldEnum.getFieldById(id);
                wrapper.orderByDesc(ObjectUtil.isNotEmpty(field), field);
            }
        }
        if (ObjectUtil.isNull(condition.getOrderByAsc()) || condition.getOrderByAsc().isEmpty()){
            if (ObjectUtil.isNull(condition.getOrderByDesc()) || condition.getOrderByDesc().isEmpty()){
                wrapper.orderByDesc("sequence");
            }
        }
        Page<ArticleList> result = articleListMapper.selectPage(new Page<>(condition.getPage(), condition.getSize()), wrapper);
        result.getRecords().forEach(item -> {
            item.setStick(item.getSequence().equals(Long.MAX_VALUE));
        });
        return result;
    }

    @Override
    public ArticleInfo infoById(Integer id) {
        ArticleInfo articleInfo = articleInfoMapper.selectById(id);
        // 查询时增加文章阅读量
        if (articleInfo != null){
            articleMapper.updateViewCountById(id, articleInfo.getViewCount() + 1);
        }
        return articleInfo;
    }

    @Override
    public Page<ArticleListView> pageBackByCondition(ArticleQuery condition) {
        Page<ArticleListBack> articles = articleMapper.selectAllList(new Page<>(condition.getPage(), condition.getSize()), condition);
        Map<Integer, String> categoryMap = categoryCache.get().stream().collect(Collectors.toMap(CategoryList::getId, CategoryList::getName));
        List<ArticleListView> record = articles.getRecords().stream().map(item -> {
            ArticleListView view = BeanUtil.copyProperties(item, ArticleListView.class, "sequence");
            view.setSequence(item.getSequence().equals(Long.MAX_VALUE) ? ArticleOrderTypeEnum.TOP : ArticleOrderTypeEnum.TIME);
            view.setCategoryName(categoryMap.get(view.getCategoryId()));
            return view;
        }).collect(Collectors.toList());
        Page<ArticleListView> result = new Page<>();
        BeanUtil.copyProperties(articles, result, "record");
        result.setRecords(record);
        return result;
    }

    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleListMapper articleListMapper, ArticleInfoMapper articleInfoMapper, CategoryCache categoryCache) {
        this.articleMapper = articleMapper;
        this.articleListMapper = articleListMapper;
        this.articleInfoMapper = articleInfoMapper;
        this.categoryCache = categoryCache;
    }

}
