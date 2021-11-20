package xyz.esion.blog.constant;

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

}