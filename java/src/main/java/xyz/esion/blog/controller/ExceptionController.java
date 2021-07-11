package xyz.esion.blog.controller;

import cn.dev33.satoken.exception.NotLoginException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.esion.blog.global.Result;

/**
 * 异常控制器
 *
 * @author qiao shengda
 * @since 2021/6/20
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public Result exception(Exception exception){
        exception.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(NotLoginException.class)
    public Result NotLoginException(NotLoginException exception){
        return Result.fail(Result.ResultCode.UN_AUTHENTICATION).message(exception.getMessage());
    }

}
