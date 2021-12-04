package xyz.esion.blog.controller.manage;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.domain.Author;
import xyz.esion.blog.domain.Config;
import xyz.esion.blog.entity.Article;
import xyz.esion.blog.entity.Comment;
import xyz.esion.blog.enumeration.CommentSourceTypeEnum;
import xyz.esion.blog.enumeration.CommentStatusEnum;
import xyz.esion.blog.enumeration.CommentTypeEnum;
import xyz.esion.blog.global.NameConvertModel;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.service.*;
import xyz.esion.blog.view.CommentInfoView;
import xyz.esion.blog.view.PageView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Esion
 * @since 2021/12/4
 */
@RestController
@RequestMapping("manage/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PageService pageService;
    private final ArticleService articleService;

    private final ConfigService configService;
    private final AuthorService authorService;

    @GetMapping
    public Result<PageView<CommentInfoView>> page(
            @NameConvertModel PageParam pageParam,
            @RequestParam(value = "source_type", required = false, defaultValue = "0") Integer sourceType,
            @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status
    ) {
        Page<Comment> commentPage = commentService.page(new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                new QueryWrapper<Comment>().eq(sourceType > 0, "source_type", sourceType)
                        .eq(status > -1, "status", status)
                        .orderByDesc("create_time"));
        List<Comment> comments = commentPage.getRecords();
        // 查询页面和文章 - 分组
        Map<Integer, List<Comment>> collect = comments.stream().collect(Collectors.groupingBy(Comment::getSourceType));
        List<Integer> pageIds = collect
                .getOrDefault(CommentSourceTypeEnum.PAGE.getValue(), new LinkedList<>())
                .stream()
                .map(Comment::getSourceId)
                .distinct()
                .collect(Collectors.toList());
        List<Integer> articleIds = collect
                .getOrDefault(CommentSourceTypeEnum.ARTICLE.getValue(), new LinkedList<>())
                .stream()
                .map(Comment::getSourceId)
                .distinct()
                .collect(Collectors.toList());
        // 查询页面
        Map<Integer, xyz.esion.blog.entity.Page> commentPageMap = pageIds.isEmpty() ? new HashMap<>(0) : pageService
                .listByIds(pageIds)
                .stream()
                .collect(Collectors.toMap(xyz.esion.blog.entity.Page::getId, e -> e));
        // 查询文章
        Map<Integer, Article> commentArticleMap = articleIds.isEmpty() ? new HashMap<>(0) : articleService.listByIds(articleIds)
                .stream()
                .collect(Collectors.toMap(Article::getId, e -> e));
        return Result.success(new PageView<>(
                commentPage.getCurrent(),
                commentPage.getSize(),
                commentPage.getPages(),
                commentPage.getTotal(),
                comments.stream().map(item -> {
                    CommentInfoView view = BeanUtil.copyProperties(item, CommentInfoView.class);
                    if (item.getSourceType().equals(CommentSourceTypeEnum.PAGE.getValue())) {
                        view.setSourceTitle(commentPageMap.containsKey(view.getSourceId()) ?
                                commentPageMap.get(view.getSourceId()).getTitle() : "");
                    }else if (item.getSourceType().equals(CommentSourceTypeEnum.ARTICLE.getValue())) {
                        view.setSourceTitle(commentArticleMap.containsKey(view.getSourceId()) ?
                                commentArticleMap.get(view.getSourceId()).getTitle() : "");
                    }
                    return view;
                }).collect(Collectors.toList())
        ));
    }

    @PostMapping
    public Result<Boolean> save(@RequestBody Comment comment, HttpServletRequest request) {
        UserAgent userAgent = UserAgentUtil.parse(request.getHeader("user-agent"));
        if (userAgent == null) {
            return Result.fail("请求头缺少user-agent");
        }
        Config config = configService.info();
        Author author = authorService.info();
        comment.setIcon(config.getFavicon());
        comment.setEmail(author.getEmail());
        comment.setWebsite(config.getHref());
        comment.setNickname(author.getName());
        comment.setStatus(CommentStatusEnum.PASS.getValue());
        comment.setBrowser(userAgent.getBrowser().getName());
        comment.setSystemVersion(userAgent.getOs().getName());
        comment.setType(CommentTypeEnum.AUTHOR.getValue());
        return Result.success(commentService.save(comment));
    }

    @PutMapping("{id}")
    public Result<Boolean> update(@PathVariable Integer id, @RequestBody Comment comment) {
        comment.setId(id);
        return Result.success(commentService.updateById(comment));
    }

}
