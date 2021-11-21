package xyz.esion.blog.constant;

import java.io.File;

/**
 * 单文件常量
 *
 * @author Esion
 * @since 2021/11/20
 */
public class FileConstant {

    /**
     * 初始化密码存放文职
     */
    public static final String PASSWORD_PATH = PathConstant.HOME_PATH + File.separator + "init_password";
    /**
     * 初始化时间存放位置
     */
    public static final String INIT_DATE_TIME = PathConstant.HOME_PATH + File.separator + "init_date_time";

}
