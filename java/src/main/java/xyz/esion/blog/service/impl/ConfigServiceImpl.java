package xyz.esion.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Service;
import xyz.esion.blog.global.Config;
import xyz.esion.blog.service.ConfigService;

/**
 * @author qiao shengda
 * @since 2021/7/11
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    private Config config;

    @Override
    public Config get() {
        return this.config;
    }

    @Override
    public boolean update(Config config) {
        BeanUtil.copyProperties(config, this.config);
        return true;
    }

    public ConfigServiceImpl(Config config) {
        this.config = config;
    }
}
