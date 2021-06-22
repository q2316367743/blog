package xyz.esion.blog.service;

import xyz.esion.blog.entity.NoticeList;

import java.util.List;

/**
 * @author Esion
 * @since 2021/6/22
 */
public interface NoticeService {

    /**
     * 获取前四条通知
     * @return 通知
     * */
    List<NoticeList> list();

}
