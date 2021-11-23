package xyz.esion.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.esion.blog.entity.Page;
import xyz.esion.blog.service.PageService;
import xyz.esion.blog.mapper.PageMapper;
import org.springframework.stereotype.Service;

/**
 * @author Esion
 * @since 2021/11/17
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page>
        implements PageService {
}




