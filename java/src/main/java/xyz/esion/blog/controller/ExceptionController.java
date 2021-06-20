package xyz.esion.blog.controller;

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

}
