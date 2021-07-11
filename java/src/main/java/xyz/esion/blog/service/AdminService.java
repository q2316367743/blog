package xyz.esion.blog.service;

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
     * 更新用户名
     *
     * @param target 要更改的用户名
     * @return 修改结果
     * */
    boolean updateUsername(String target);

    /**
     * 更新密码
     *
     * @param target 要更改的密码（未加密）
     * @return 修改结果
     * */
    boolean updatePassword(String target);

}
