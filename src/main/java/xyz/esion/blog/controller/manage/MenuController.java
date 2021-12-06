package xyz.esion.blog.controller.manage;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.entity.Menu;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.MenuService;
import xyz.esion.blog.view.MenuView;

import java.util.List;

/**
 * @author Esion
 * @since 2021/12/5
 */
@RestController
@RequestMapping("manage/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public Result<List<Tree<Integer>>> list() {
        return Result.success(menuService.tree());
    }

    @PostMapping
    public Result<Boolean> save(@RequestBody Menu menu) {
        return Result.success(menuService.save(menu));
    }

    @PutMapping("{id}")
    public Result<Boolean> update(@PathVariable Integer id, @RequestBody Menu menu) {
        menu.setId(id);
        menu.setCreateTime(null);
        menu.setUpdateTime(null);
        menu.setIsDelete(null);
        return Result.success(menuService.updateById(menu));
    }

    @DeleteMapping("{id}")
    public Result<Boolean> remove(@PathVariable Integer id) {
        int menuCount = menuService.count(new QueryWrapper<Menu>().eq("p_id", id));
        if (menuCount > 0) {
            throw new IllegalArgumentException("当前节点下存在子节点，请先删除全部子节点");
        }
        return Result.success(menuService.removeById(id));
    }

}
