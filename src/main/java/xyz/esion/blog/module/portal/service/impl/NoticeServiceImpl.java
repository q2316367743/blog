package xyz.esion.blog.module.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.esion.blog.entity.Notice;
import xyz.esion.blog.mapper.NoticeMapper;
import xyz.esion.blog.module.portal.service.NoticeService;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Service("portalNotice")
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
}
