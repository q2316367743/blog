package xyz.esion.blog.service;

import xyz.esion.blog.enumeration.LoginStatusEnum;

/**
 * @author Esion
 * @since 2021/11/20
 */
public interface AdminService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param mfa MFA认证
     * @return 用户信息
     */
    LoginStatusEnum login(String username, String password, String mfa);

}
