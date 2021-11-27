package xyz.esion.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.esion.blog.entity.Link;
import xyz.esion.blog.service.LinkService;
import xyz.esion.blog.mapper.LinkMapper;
import org.springframework.stereotype.Service;

/**
 * @author Esion
 * @since 2021/11/27
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService{

}




