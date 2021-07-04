package xyz.esion.blog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.esion.blog.entity.ArticleInfo;
import xyz.esion.blog.entity.ArticleList;
import xyz.esion.blog.entity.dto.ArticleQueryDTO;
import xyz.esion.blog.enumerate.ArticleOrderEnum;
import xyz.esion.blog.mapper.ArticleListMapper;
import xyz.esion.blog.mapper.ArticleMapper;
import xyz.esion.blog.mapper.ArticleInfoMapper;
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
            for (Integer id : condition.getOrderByAsc()){
                String field = ArticleOrderEnum.getFieldById(id);
                wrapper.orderByAsc(ObjectUtil.isNotEmpty(field), field);
            }
        }
        if (ObjectUtil.isNotNull(condition.getOrderByDesc()) && !condition.getOrderByDesc().isEmpty()){
            for (Integer id : condition.getOrderByDesc()){
                String field = ArticleOrderEnum.getFieldById(id);
                wrapper.orderByDesc(ObjectUtil.isNotEmpty(field), field);
            }
        }
        if (ObjectUtil.isNull(condition.getOrderByAsc()) || condition.getOrderByAsc().isEmpty()){
            if (ObjectUtil.isNull(condition.getOrderByDesc()) || condition.getOrderByDesc().isEmpty()){
                wrapper.orderByDesc("sequence");
            }
        }
        return articleListMapper.selectPage(new Page<>(condition.getPage(), condition.getSize()), wrapper);
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

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleListMapper articleListMapper, ArticleInfoMapper articleInfoMapper) {
        this.articleMapper = articleMapper;
        this.articleListMapper = articleListMapper;
        this.articleInfoMapper = articleInfoMapper;
    }
}
