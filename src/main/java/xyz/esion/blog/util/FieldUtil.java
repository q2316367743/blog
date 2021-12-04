package xyz.esion.blog.util;

import cn.hutool.core.util.StrUtil;

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

}
