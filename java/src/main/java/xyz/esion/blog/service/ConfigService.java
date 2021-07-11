package xyz.esion.blog.service;

import xyz.esion.blog.global.Config;

/**
 * @author qiao shengda
 * @since 2021/7/11
 */
public interface ConfigService {

    /**
     * 获取网站配置
     *
     * @return 网站配置信息
     * */
    Config get();

    /**
     * 更新网站配置
     *
     * @param config 网站配置
     * @return 操作结果
     * */
    boolean update(Config config);

}
