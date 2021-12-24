package xyz.esion.blog.service;

import xyz.esion.blog.entity.Theme;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Esion
 * @since 2021/12/24
 */
public interface ThemeService extends IService<Theme> {

    /**
     * 启用某个主题
     *
     * @param id 主题ID
     * @return 结果
     */
    Boolean enable(Integer id);

    /**
     * 同步某个主题
     * @param id 主题ID
     * @return 结果
     */
    Boolean sync(Integer id);

}
