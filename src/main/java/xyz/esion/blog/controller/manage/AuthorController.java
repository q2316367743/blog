package xyz.esion.blog.controller.manage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.domain.Author;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.AuthorService;

/**
 * @author Esion
 * @since 2021/11/25
 */
@RestController
@RequestMapping("manage/api/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public Result<Author> info() {
        return Result.success(authorService.info());
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody Author author) {
        authorService.update(author);
        return Result.success(true);
    }

}
