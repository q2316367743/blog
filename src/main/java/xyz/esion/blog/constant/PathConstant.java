package xyz.esion.blog.constant;

import cn.hutool.core.io.FileUtil;
import cn.hutool.system.SystemUtil;

import java.io.File;

/**
 * 目录常量
 *
 * @author Esion
 * @since 2021/11/20
 */
public class PathConstant {

    /**
     * 项目主要目录
     */
    public static final String HOME_PATH = SystemUtil.getUserInfo().getHomeDir() + File.separator + ".blog";

    /**
     * 自定义主题路径
     */
    public static final String TEMPLATES_PATH = HOME_PATH + File.separator + "templates";

    /**
     * 静态文件路径
     */
    public static final String STATIC_PATH = HOME_PATH + File.separator + "static";

    /**
     * 备份目录
     */
    public static final String BACKUP_PATH = HOME_PATH + File.separator + "backup";

    /**
     * 图片目录
     */
    public static final String IMAGE_PATH = HOME_PATH + File.separator + "image";

    /**
     * 音乐目录
     */
    public static final String MUSIC_PATH = HOME_PATH + File.separator + "music";

    /**
     * 资源路径
     */
    public static final String RESOURCE_PATH = HOME_PATH + File.separator + "resource";

    /**
     * 主题路径
     */
    public static final String THEME_PATH = HOME_PATH + File.separator + "themes";

}
