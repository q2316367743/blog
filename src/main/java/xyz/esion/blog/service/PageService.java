package xyz.esion.blog.service;

import xyz.esion.blog.condition.PageCondition;
import xyz.esion.blog.entity.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.view.PageListView;
import xyz.esion.blog.view.PageView;

/**
 * @author Esion
 * @since 2021/11/17
 */
public interface PageService extends IService<Page> {

    /**
     * 分页查询列表
     * @param pageParam 分页参数
     * @param condition 查询参数
     * @return 列表
     */
    PageView<PageListView> pageBase(PageParam pageParam, PageCondition condition);

}
