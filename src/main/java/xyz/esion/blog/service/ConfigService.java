package xyz.esion.blog.service;

import xyz.esion.blog.domain.Config;

/**
 * @author Esion
 * @since 2021/11/20
 */
public interface ConfigService {

    /**
     * 查询配置
     *
     * @return 配置信息
     */
    Config info();

}
