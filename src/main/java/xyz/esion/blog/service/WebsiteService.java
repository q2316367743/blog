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
     * 更新网站信息
     */
    void update();

}
