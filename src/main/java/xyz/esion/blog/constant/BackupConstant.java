package xyz.esion.blog.constant;

import java.io.File;

/**
 * 备份常量
 *
 * @author Esion
 * @since 2021/11/20
 */
public class BackupConstant {

    /**
     * 作者缓存路径
     */
    public static final String AUTHOR_PATH = PathConstant.BACKUP_PATH + File.separator + "author.json";

    /**
     * 配置缓存路径
     */
    public static final String CONFIG_PATH = PathConstant.BACKUP_PATH + File.separator + "config.json";

    /**
     * 网站信息缓存路径
     */
    public static final String WEBSITE_PATH = PathConstant.BACKUP_PATH + File.separator + "website.json";

}
