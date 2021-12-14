package xyz.esion.blog.service;

import xyz.esion.blog.domain.Website;

/**
 * @author Esion
 * @since 2021/11/20
 */
public interface WebsiteService {

    /**
     * 获取网站统计信息
     *
     * @return 网站统计信息
     */
    Website info();

    /**
     * 同步任务，每10分钟同步一次到内存中
     */
    void sync();

    /**
     * 网站访问量增加
     */
    void accountCountIncrement();

}
