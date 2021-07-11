package xyz.esion.blog.controller.manage;

import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.global.Author;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.AuthorService;

/**
 * @author Esion
 * @since 2021/7/11
 */
@RestController
@RequestMapping("manage/author")
public class AuthorController {

    private AuthorService authorService;

    @GetMapping
    public Result info(){
        return Result.success().data("item", authorService.getAll());
    }

    @PutMapping
    public Result update(@RequestBody Author author){
        return Result.choose(authorService.update(author));
    }

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
}
