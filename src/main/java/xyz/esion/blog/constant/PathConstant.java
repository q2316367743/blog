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
     * 网络分隔符
     */
    public static final String WEB_SEPARATOR = "/";

    /**
     * 项目主要目录
     */
    public static final String HOME_PATH = SystemUtil.getUserInfo().getHomeDir() + File.separator + ".blog";

    /**
     * 自定义主题路径
     */
    public static final String TEMPLATES_PATH = HOME_PATH + File.separator + FolderConstant.TEMPLATES_FOLDER;

    /**
     * 静态文件路径
     */
    public static final String STATIC_PATH = HOME_PATH + File.separator + FolderConstant.STATIC_FOLDER;

    /**
     * 备份目录
     */
    public static final String BACKUP_PATH = HOME_PATH + File.separator + FolderConstant.BACKUP_FOLDER;

    /**
     * 主题路径
     */
    public static final String THEME_PATH = HOME_PATH + File.separator + FolderConstant.THEME_FOLDER;

    /**
     * 资源路径
     */
    public static final String RESOURCE_PATH = HOME_PATH + File.separator + FolderConstant.RESOURCE_FOLDER;

    /**
     * 图片资源目录
     */
    public static final String IMAGE_PATH = RESOURCE_PATH + File.separator + FolderConstant.IMAGE_FOLDER;

    /**
     * 音乐资源目录
     */
    public static final String MUSIC_PATH = RESOURCE_PATH + File.separator + FolderConstant.MUSIC_FOLDER;

    /**
     * 文章资源目录
     */
    public static final String ARTICLE_PATH = RESOURCE_PATH + File.separator + FolderConstant.ARTICLE_FOLDER;

    /**
     * 页面资源目录
     */
    public static final String PAGE_PATH = RESOURCE_PATH + File.separator + FolderConstant.PAGE_FOLDER;

}
