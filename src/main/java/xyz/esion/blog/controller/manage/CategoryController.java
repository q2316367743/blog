package xyz.esion.blog.controller.manage;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.entity.Category;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.CategoryService;
import xyz.esion.blog.view.CategoryView;

import java.util.List;

/**
 * @author Esion
 * @since 2021/12/6
 */
@RestController
@RequestMapping("manage/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Result<List<CategoryView>> tree() {
        return Result.success(categoryService.tree());
    }

    @PostMapping
    public Result<Boolean> save(@RequestBody Category category) {
        if (StrUtil.isBlank(category.getName())) {
            throw new IllegalArgumentException("请输入分类名称");
        }
        return Result.success(categoryService.save(category));
    }

    @PutMapping("{id}")
    public Result<Boolean> update(@PathVariable Integer id, @RequestBody Category category) {
        category.setId(id);
        return Result.success(categoryService.updateById(category));
    }

}
