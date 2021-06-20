package xyz.esion.blog.controller.portal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.global.Result;

/**
 * 文章控制器
 *
 * @author Esion
 * @since 2021/6/20
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @GetMapping
    public Result page(Integer page, Integer size){
        return Result.success();
    }

}
