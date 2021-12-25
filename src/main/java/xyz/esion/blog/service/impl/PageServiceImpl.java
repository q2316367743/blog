package xyz.esion.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.esion.blog.condition.PageCondition;
import xyz.esion.blog.entity.Page;
import xyz.esion.blog.mapper.PageMapper;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.service.PageService;
import xyz.esion.blog.view.PageListView;
import xyz.esion.blog.view.PageView;

import java.util.stream.Collectors;

/**
 * @author Esion
 * @since 2021/11/17
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page>
        implements PageService {

    @Override
    public PageView<PageListView> pageBase(PageParam pageParam, PageCondition condition) {
        QueryWrapper<Page> queryWrapper = new QueryWrapper<Page>()
                .like(StrUtil.isNotBlank(condition.getTitle()),
                        "title", condition.getTitle());
        if (condition.getOrderBy() != null) {
            queryWrapper.orderBy(CollUtil.isNotEmpty(condition.getOrderBy()), true, condition.getOrderBy());
        }
        queryWrapper.eq(condition.getStatus() != null, "status", condition.getStatus());
        if (condition.getOrderByDesc() != null) {
            queryWrapper.orderByDesc(CollUtil.isNotEmpty(condition.getOrderByDesc()), condition.getOrderByDesc());
        }
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Page> pagePage = this.baseMapper.selectPage(
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                queryWrapper);
        return new PageView<>(
                pagePage.getCurrent(),
                pagePage.getSize(),
                pagePage.getPages(),
                pagePage.getTotal(),
                pagePage.getRecords()
                        .stream()
                        .map(item -> BeanUtil.copyProperties(item, PageListView.class))
                        .collect(Collectors.toList()));
    }
}




