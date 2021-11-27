package xyz.esion.blog.controller.manage;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.condition.LinkCondition;
import xyz.esion.blog.entity.Link;
import xyz.esion.blog.global.NameConvertModel;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.param.LinkParam;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.service.LinkService;

/**
 * @author Esion
 * @since 2021/11/27
 */
@RestController
@RequestMapping("/manage/api/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping
    public Result<Page<Link>> page(
            @NameConvertModel PageParam pageParam,
            @NameConvertModel LinkCondition condition
    ) {
        return Result.success(linkService.page(
                new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                new QueryWrapper<Link>().eq("status", condition.getStatus())
                        .like("name", condition.getName())
        ));
    }

    @PostMapping
    public Result<Boolean> save(@RequestBody LinkParam param) {
        if (StrUtil.isBlank(param.getName())) {
            throw new IllegalArgumentException("昵称不能为空");
        }
        param.setId(null);
        if (StrUtil.isBlank(param.getUrl())) {
            throw new IllegalArgumentException("链接不能为空");
        }
        Link link = BeanUtil.copyProperties(param, Link.class);
        return Result.success(linkService.save(link));
    }

    @PutMapping("{id}")
    public Result<Boolean> update(
            @PathVariable Integer id,
            @RequestBody LinkParam param
    ) {
        if (StrUtil.isBlank(param.getName())) {
            throw new IllegalArgumentException("昵称不能为空");
        }
        param.setId(id);
        if (StrUtil.isBlank(param.getUrl())) {
            throw new IllegalArgumentException("链接不能为空");
        }
        Link link = BeanUtil.copyProperties(param, Link.class);
        return Result.success(linkService.updateById(link));
    }

    @DeleteMapping("{id}")
    public Result<Boolean> remove(@PathVariable Integer id) {
        return Result.success(linkService.removeById(id));
    }

}
