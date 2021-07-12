package xyz.esion.blog.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.entity.dto.CommentSave;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.CommentService;

/**
 * @author Esion
 * @since 2021/7/4
 */
@RestController
@RequestMapping("api/comment")
public class CommentController {

    private CommentService commentService;

    @GetMapping("{articleId}")
    public Result all(@PathVariable("articleId") Integer articleId){
        return Result.success().items(commentService.listByFront(articleId));
    }

    @PostMapping
    public Result save(@RequestBody CommentSave commentSave){
        return Result.choose(commentService.save(commentSave) > 0);
    }

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
}
