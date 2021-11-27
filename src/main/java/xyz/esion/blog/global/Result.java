package xyz.esion.blog.global;

import lombok.Data;
import xyz.esion.blog.enumeration.ResultCodeEnum;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static Result<Boolean> success() {
        Result<Boolean> result = new Result<>();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(true);
        return result;
    }

    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg("失败");
        result.setData(null);
        return result;
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }


    public static <T> Result<T> fail(ResultCodeEnum code) {
        Result<T> result = new Result<>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(null);
        return result;
    }

}
