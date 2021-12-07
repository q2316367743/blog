package xyz.esion.blog.controller.manage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.condition.PageCondition;
import xyz.esion.blog.entity.Page;
import xyz.esion.blog.global.NameConvertModel;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.service.PageService;
import xyz.esion.blog.view.PageListView;
import xyz.esion.blog.view.PageView;

/**
 * @author Esion
 * @since 2021/12/7
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("manage/api/page")
public class PageController {

    private final PageService pageService;

    @GetMapping
    public Result<PageView<PageListView>> page(
            @NameConvertModel PageParam pageParam,
            @NameConvertModel PageCondition condition
    ){
        return Result.success(pageService.pageBase(pageParam, condition));
    }

    @GetMapping("{id}")
    public Result<Page> info(@PathVariable Integer id) {
        return Result.success(pageService.getById(id));
    }

    @PostMapping
    public Result<Boolean> save(@RequestBody Page page) {
        return Result.success(pageService.save(page));
    }

    @PutMapping("{id}")
    public Result<Boolean> update(@PathVariable Integer id, @RequestBody Page page) {
        page.setId(id);
        return Result.success(pageService.updateById(page));
    }

    @DeleteMapping("{id}")
    public Result<Boolean> remove(@PathVariable Integer id) {
        return Result.success(pageService.removeById(id));
    }

}
