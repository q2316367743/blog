package xyz.esion.blog.enumeration;

/**
 * @author Esion
 * @since 2021/11/20
 */
public enum LoginStatusEnum {

    /**
     * 登录成功
     */
    SUCCESS,
    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR,
    /**
     * 需要MFA认证
     */
    NEED_MFA;

}
