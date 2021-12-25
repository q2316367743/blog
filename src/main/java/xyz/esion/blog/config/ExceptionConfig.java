package xyz.esion.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.esion.blog.global.PageException;
import xyz.esion.blog.global.Result;

/**
 * @author Esion
 * @since 2021/11/26
 */
@ControllerAdvice
@Slf4j
public class ExceptionConfig {

    @ExceptionHandler(PageException.class)
    public String pageException(PageException e) {
        log.error(e.getMessage(), e);
        return "error/500";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Result<Boolean> illegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Boolean> exception(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Boolean> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

}
