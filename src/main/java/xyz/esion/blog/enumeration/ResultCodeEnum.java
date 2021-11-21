package xyz.esion.blog.enumeration;

/**
 * @author Esion
 * @since 2021/11/16
 */
public enum ResultCodeEnum {

    // 返回状态码
    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    UN_AUTHENTICATION(401, "未认证"),
    INVALID(402, "无效的认证码"),
    NOT_FOUND(404, "未找到资源");

    private final Integer code;
    private final String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
