package xyz.esion.blog.controller.portal;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.condition.ArticleCondition;
import xyz.esion.blog.entity.*;
import xyz.esion.blog.enumeration.CommentSourceTypeEnum;
import xyz.esion.blog.enumeration.CommentStatusEnum;
import xyz.esion.blog.enumeration.CommentTypeEnum;
import xyz.esion.blog.global.NameConvertModel;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.param.CommentParam;
import xyz.esion.blog.param.LinkParam;
import xyz.esion.blog.param.MessageParam;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.service.*;
import xyz.esion.blog.util.FieldUtil;
import xyz.esion.blog.view.PageView;
import xyz.esion.blog.view.article.ArticleListView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Esion
 * @since 2021/11/26
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ApiController {

    private static final String PRIMARY_KEY = "id";

    private final MessageService messageService;
    private final LinkService linkService;
    private final ArticleService articleService;
    private final CommentService commentService;
    private final PageService pageService;

    @PostMapping("message")
    public Result<Boolean> messageSave(@Validated @RequestBody MessageParam param) {
        Message message = BeanUtil.copyProperties(param, Message.class);
        return Result.success(messageService.save(message));
    }

    @PostMapping("link")
    public Result<Boolean> linkSave(@Validated @RequestBody LinkParam param) {
        param.setId(null);
        param.setStatus(null);
        param.setReason(null);
        Link link = BeanUtil.copyProperties(param, Link.class);
        if (StrUtil.isBlank(link.getIcon())) {
            link.setIcon(FieldUtil.getIconByEmailOrWebsite(link.getEmail(), link.getUrl()));
        }
        return Result.success(linkService.save(link));
    }

    @GetMapping("article")
    public Result<PageView<ArticleListView>> articlePage(
            @NameConvertModel PageParam pageParam,
            @NameConvertModel ArticleCondition condition
    ) {
        return Result.success(articleService.search(pageParam, condition));
    }

    @PostMapping("comment")
    public Result<Boolean> commentSave(
            @Validated @RequestBody CommentParam param,
            HttpServletRequest request
    ) {
        UserAgent userAgent = UserAgentUtil.parse(request.getHeader("user-agent"));
        if (userAgent == null) {
            return Result.fail("请求头缺少user-agent");
        }
        CommentSourceTypeEnum sourceType = CommentSourceTypeEnum.getByValue(param.getSourceType());
        if (sourceType == null) {
            throw new IllegalArgumentException("来源类型错误");
        }
        if (sourceType.equals(CommentSourceTypeEnum.ARTICLE)) {
            if (articleService.count(new QueryWrapper<Article>().eq(PRIMARY_KEY, param.getSourceId())) == 0) {
                throw new IllegalArgumentException("来源ID不存在");
            }
        } else if (sourceType.equals(CommentSourceTypeEnum.PAGE)) {
            if (pageService.count(new QueryWrapper<Page>().eq(PRIMARY_KEY, param.getSourceId())) == 0) {
                throw new IllegalArgumentException("来源ID不存在");
            }
        }
        Comment comment = new Comment();
        comment.setEmail(param.getEmail());
        comment.setWebsite(param.getWebsite());
        comment.setIcon(FieldUtil.getIconByEmailOrWebsite(comment.getEmail(), comment.getWebsite()));
        comment.setNickname(param.getNickname());
        comment.setStatus(CommentStatusEnum.APPLY.getValue());
        comment.setBrowser(userAgent.getBrowser().getName());
        comment.setSystemVersion(userAgent.getOs().getName());
        // 通过友链判断类型
        Link link = linkService.getOne(new QueryWrapper<Link>()
                .eq("url", comment.getWebsite())
                .last("limit 1"));
        if (link != null) {
            comment.setType(CommentTypeEnum.FRIEND.getValue());
        } else {
            comment.setType(CommentTypeEnum.VISITOR.getValue());
        }
        comment.setSourceId(param.getSourceId());
        comment.setSourceType(param.getSourceType());
        comment.setRootId(param.getRootId());
        Comment target = commentService.getById(param.getTargetId());
        if (target != null) {
            comment.setTargetId(target.getId());
            comment.setTargetNickname(target.getNickname());
            comment.setTargetWebsite(target.getWebsite());
        }
        comment.setContent(param.getContent());
        return Result.success(commentService.save(comment));
    }

}
