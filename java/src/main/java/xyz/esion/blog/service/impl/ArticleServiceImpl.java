package xyz.esion.blog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.esion.blog.entity.ArticleInfo;
import xyz.esion.blog.entity.ArticleList;
import xyz.esion.blog.entity.dto.ArticleQueryDTO;
import xyz.esion.blog.mapper.ArticleInfoMapper;
import xyz.esion.blog.mapper.ArticleListMapper;
import xyz.esion.blog.mapper.ArticleMapper;
import xyz.esion.blog.service.ArticleService;

/**
 * @author qiao shengda
 * @since 2021/6/21
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleMapper articleMapper;
    private ArticleListMapper articleListMapper;
    private ArticleInfoMapper articleInfoMapper;

    @Override
    public Page<ArticleList> pageByCondition(ArticleQueryDTO condition) {
        QueryWrapper<ArticleList> wrapper = new QueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(condition.getTitle()), "title", condition.getTitle());
        wrapper.eq(ObjectUtil.isNotEmpty(condition.getCategoryId()), "category_id", condition.getCategoryId());
        if (ObjectUtil.isNotNull(condition.getOrderByAsc()) && !condition.getOrderByAsc().isEmpty()){
            for (String field : condition.getOrderByAsc()){
                wrapper.orderByAsc(ObjectUtil.isNotEmpty(field), field);
            }
        }
        if (ObjectUtil.isNotNull(condition.getOrderByDesc()) && !condition.getOrderByDesc().isEmpty()){
            for (String field : condition.getOrderByDesc()){
                wrapper.orderByDesc(ObjectUtil.isNotEmpty(field), field);
            }
        }
        return articleListMapper.selectPage(new Page<>(condition.getPage(), condition.getSize()), wrapper);
    }

    @Override
    public ArticleInfo infoById(Integer id) {
        return articleInfoMapper.selectById(id);
    }

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleListMapper articleListMapper, ArticleInfoMapper articleInfoMapper) {
        this.articleMapper = articleMapper;
        this.articleListMapper = articleListMapper;
        this.articleInfoMapper = articleInfoMapper;
    }
}
