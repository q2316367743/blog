package xyz.esion.blog.module.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.esion.blog.entity.Page;
import xyz.esion.blog.module.portal.service.PageService;
import xyz.esion.blog.mapper.PageMapper;
import org.springframework.stereotype.Service;

/**
 * @author Esion
 * @since 2021/11/17
 */
@Service("portalPage")
public class PageServiceImpl extends ServiceImpl<PageMapper, Page>
        implements PageService {

}




