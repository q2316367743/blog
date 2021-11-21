package xyz.esion.blog.service;

import xyz.esion.blog.entity.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.esion.blog.view.PageInfoView;

/**
 * @author Esion
 * @since 2021/11/17
 */
public interface PageService extends IService<Page> {

    /**
     * 根据identification查询页面
     *
     * @param identification 唯一标识
     * @return 页面内容
     */
    PageInfoView selectByIdentification(String identification);

}
