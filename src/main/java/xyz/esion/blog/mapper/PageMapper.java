package xyz.esion.blog.mapper;

import xyz.esion.blog.entity.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.esion.blog.view.PageInfoView;

/**
 * @Entity xyz.esion.blog.entity.Page
 * @author Esion
 * @since 2021/11/17
 */
public interface PageMapper extends BaseMapper<Page> {

    /**
     * 根据identification查询页面
     *
     * @param identification 唯一标识
     * @return 页面内容
     */
    PageInfoView selectByIdentification(String identification);

}




