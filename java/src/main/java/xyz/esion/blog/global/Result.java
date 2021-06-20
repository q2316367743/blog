package xyz.esion.blog.global;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 结果集
 *
 * @author qiao shengda
 * @since 2021/6/20
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public Result() {
    }

    private Result(Boolean success, ResultCode resultCode) {
        this.success = success;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Result code(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        return this;
    }

    public Result message(String message){
        this.message = message;
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public static Result success(){
        Result r = new Result(true, ResultCode.SUCCESS);
        return r;
    }

    public static Result fail(){
        Result r = new Result(false, ResultCode.FAIL);
        return r;
    }

    public static Result choose(boolean flag){
        return flag ? success() : fail();
    }

    public enum ResultCode{
        // 成功
        SUCCESS(200, "成功"),
        // 失败
        FAIL(500, "失败"),
        // 未认证
        UN_AUTHENTICATION(401, "未认证"),
        // 未授权
        UN_AUTHORIZE(403, "未授权");

        private Integer code;
        private String message;

        ResultCode(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


}
