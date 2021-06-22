package xyz.esion.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.esion.blog.entity.NoticeList;
import xyz.esion.blog.mapper.NoticeListMapper;
import xyz.esion.blog.mapper.NoticeMapper;
import xyz.esion.blog.service.NoticeService;

import java.util.List;

/**
 * @author Esion
 * @since 2021/6/22
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeMapper noticeMapper;
    private NoticeListMapper noticeListMapper;

    @Override
    public List<NoticeList> list() {
        return noticeListMapper.selectList(new QueryWrapper<>());
    }

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper, NoticeListMapper noticeListMapper) {
        this.noticeMapper = noticeMapper;
        this.noticeListMapper = noticeListMapper;
    }
}
