package xyz.esion.blog.service;

import xyz.esion.blog.global.Admin;

/**
 * @author Esion
 * @since 2021/7/11
 */
public interface AdminService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     * */
    boolean login(String username, String password);

    /**
     * 更新管理员
     *
     * @param admin 管理员信息
     * @return 修改结果
     * */
    boolean update(Admin admin);

}
