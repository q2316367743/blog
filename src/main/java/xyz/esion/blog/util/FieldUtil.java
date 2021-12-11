package xyz.esion.blog.util;

import cn.hutool.core.util.StrUtil;
import xyz.esion.blog.constant.PathConstant;

import java.io.File;

/**
 * @author Esion
 * @since 2021/12/4
 */
public class FieldUtil {

    private static final String QQ_EMAIL = "@qq.com";
    private static final String FAVICON = "/favicon.ico";

    public static String getIconByEmailOrWebsite(String email, String website) {
        if (StrUtil.isNotBlank(email)) {
            if (email.contains(QQ_EMAIL)) {
                return StrUtil.format("http://q2.qlogo.cn/headimg_dl?dst_uin={}&spec=100",
                        email.replace(QQ_EMAIL, ""));
            } else {
                return website + FAVICON;
            }
        } else {
            return website + FAVICON;
        }
    }

    /**
     * 美化结果路径
     * <li>分隔符转换</li>
     * <li>路径抹除</li>
     *
     * @param path 需要美化的路径
     * @param prefix 需要删除的前缀
     * @return 路径
     */
    public static String buildResultPath(String path, String prefix) {
        if (path.length() > prefix.length()) {
            return path.substring(prefix.length()).replace(File.separator, PathConstant.WEB_SEPARATOR);
        }
        return path;
    }

}
