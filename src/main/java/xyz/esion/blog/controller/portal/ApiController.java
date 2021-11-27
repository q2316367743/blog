package xyz.esion.blog.controller.portal;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.condition.ArticleCondition;
import xyz.esion.blog.entity.Article;
import xyz.esion.blog.entity.Link;
import xyz.esion.blog.entity.Message;
import xyz.esion.blog.global.NameConvertModel;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.param.LinkParam;
import xyz.esion.blog.param.MessageParam;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.service.ArticleService;
import xyz.esion.blog.service.LinkService;
import xyz.esion.blog.service.MessageService;
import xyz.esion.blog.view.ArticleListView;
import xyz.esion.blog.view.PageView;

/**
 * @author Esion
 * @since 2021/11/26
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ApiController {

    private final MessageService messageService;
    private final LinkService linkService;
    private final ArticleService articleService;

    @PostMapping("message")
    public Result<Boolean> messageSave(@RequestBody MessageParam param){
        if (param.getType() == null) {
            throw new IllegalArgumentException("类型不能为空");
        }
        if (StrUtil.isBlank(param.getName())) {
            throw new IllegalArgumentException("昵称不能为空");
        }
        if (StrUtil.isBlank(param.getEmail())) {
            throw new IllegalArgumentException("邮箱不能为空");
        }
        if (StrUtil.isBlank(param.getContent())) {
            throw new IllegalArgumentException("内容不能为空");
        }
        Message message = BeanUtil.copyProperties(param, Message.class);
        return Result.success(messageService.save(message));
    }

    @PostMapping("link")
    public Result<Boolean> linkSave(@RequestBody LinkParam param) {
        param.setId(null);
        if (StrUtil.isBlank(param.getName())) {
            throw new IllegalArgumentException("昵称不能为空");
        }
        if (StrUtil.isBlank(param.getUrl())) {
            throw new IllegalArgumentException("链接不能为空");
        }
        Link link = BeanUtil.copyProperties(param, Link.class);
        return Result.success(linkService.save(link));
    }

    @GetMapping("article")
    public Result<PageView<ArticleListView>> articlePage(
            @NameConvertModel PageParam pageParam,
            @NameConvertModel ArticleCondition condition
            ){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>()
                .like(StrUtil.isNotBlank(condition.getTitle()),
                        "title", condition.getTitle());
        if (condition.getOrderByDesc() != null) {
            queryWrapper.orderByDesc(CollUtil.isNotEmpty(condition.getOrderByDesc()),
                    ArrayUtil.toArray(condition.getOrderByDesc(), String.class));
        }
        if (condition.getOrderBy() != null) {
            queryWrapper.orderBy(CollUtil.isNotEmpty(condition.getOrderBy()),
                    true,
                    ArrayUtil.toArray(condition.getOrderBy(), String.class));
        }
        queryWrapper.eq("status", 1);
        return Result.success(articleService.page(pageParam,queryWrapper));
    }

}
