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
import xyz.esion.blog.view.PageView;

/**
 * @author Esion
 * @since 2021/11/27
 */
@RestController
@RequestMapping("/manage/api/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    private static final String QQ_EMAIL = "@qq.com";
    private static final String FAVICON = "/favicon.ico";

    @GetMapping
    public Result<PageView<Link>> page(
            @NameConvertModel PageParam pageParam,
            @NameConvertModel LinkCondition condition
    ) {
        Page<Link> linkPage = linkService.page(
                new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                new QueryWrapper<Link>().eq(condition.getStatus() != null, "status", condition.getStatus())
                        .like(StrUtil.isNotBlank(condition.getName()), "name", condition.getName())
                        .orderByDesc("create_time")
        );
        return Result.success(new PageView<>(
                linkPage.getCurrent(),
                linkPage.getSize(),
                linkPage.getPages(),
                linkPage.getTotal(),
                linkPage.getRecords()));
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
        // 如果是qq邮箱，则使用qq头像，否则取网站图标
        if (StrUtil.isNotBlank(link.getEmail())) {
            if (link.getEmail().contains(QQ_EMAIL)) {
                String qq = link.getEmail().replace(QQ_EMAIL, "");
                link.setIcon(StrUtil.format("http://q2.qlogo.cn/headimg_dl?dst_uin={}&spec=100", qq));
            }else {
                link.setIcon(link.getUrl() + FAVICON);
            }
        }else {
            link.setIcon(link.getUrl() + FAVICON);
        }
        return Result.success(linkService.save(link));
    }

    @PutMapping("{id}")
    public Result<Boolean> update(
            @PathVariable Integer id,
            @RequestBody LinkParam param
    ) {
        Link link = BeanUtil.copyProperties(param, Link.class);
        link.setId(id);
        return Result.success(linkService.updateById(link));
    }

    @DeleteMapping("{id}")
    public Result<Boolean> remove(@PathVariable Integer id) {
        return Result.success(linkService.removeById(id));
    }

}
