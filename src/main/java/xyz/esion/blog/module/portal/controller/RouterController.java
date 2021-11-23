package xyz.esion.blog.module.portal.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.esion.blog.entity.Article;
import xyz.esion.blog.entity.*;
import xyz.esion.blog.enumeration.MessageTypeEnum;
import xyz.esion.blog.global.*;
import xyz.esion.blog.view.ArticleInfoView;
import xyz.esion.blog.view.ArticleListView;
import xyz.esion.blog.view.CategoryView;
import xyz.esion.blog.view.PageInfoView;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.service.*;
import xyz.esion.blog.view.PageView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Controller("portalRouter")
@RequestMapping
@RequiredArgsConstructor
public class RouterController {

    private final ArticleService articleService;
    private final NoticeService noticeService;
    private final CategoryService categoryService;
    private final MenuService menuService;
    private final PageService pageService;
    private final MessageService messageService;

    private final AuthorService authorService;
    private final ConfigService configService;
    private final WebsiteService websiteService;

    /**
     * 前置加载项，所有请求都会加载
     */
    private void preLoad(Model model) {
        model.addAttribute("author", authorService.info());
        model.addAttribute("config", configService.info());
        model.addAttribute("website", websiteService.info());
        model.addAttribute("menus", menuService.tree());
    }

    @GetMapping
    public String index(@NameConvertModel PageParam pageParam, Model model) {
        preLoad(model);
        PageView<ArticleListView> page = articleService.page(pageParam, new QueryWrapper<Article>()
                .eq("status", 1));
        model.addAttribute("page", page);
        return "index";
    }

    @GetMapping("index.html")
    public String home(@NameConvertModel PageParam pageParam, Model model) {
        return index(pageParam, model);
    }

    @GetMapping("article/{id}.html")
    public String article(@PathVariable String id, Model model) {
        preLoad(model);
        Article article = articleService.getById(id);
        if (article == null) {
            return "error/404";
        }
        Category category = categoryService.getById(article.getCategoryId());
        ArticleInfoView view = new ArticleInfoView();
        view.setId(article.getId());
        view.setTitle(article.getTitle());
        view.setImage(article.getImage());
        view.setCategoryId(article.getCategoryId());
        if (category != null) {
            view.setCategoryName(category.getName());
        }
        view.setTags(Arrays.asList(article.getTags().split(",")));
        view.setDescription(article.getDescription());
        view.setCreateTime(article.getCreateTime());
        view.setUpdateTime(article.getUpdateTime());
        view.setWordCount(article.getWordCount());
        view.setReadTime(article.getReadTime());
        view.setViewCount(article.getViewCount());
        view.setCommentCount(article.getCommentCount());
        view.setContent(article.getContent());
        model.addAttribute("article", view);
        return "article";
    }

    @GetMapping("/page/{id}.html")
    public String page(@PathVariable String id, Model model) {
        preLoad(model);
        xyz.esion.blog.entity.Page page = pageService.getById(id);
        if (page == null) {
            return "error/404";
        }
        PageInfoView view = BeanUtil.copyProperties(page, PageInfoView.class);
        model.addAttribute("page", view);
        return "page";
    }

    @GetMapping("category.html")
    public String category(Model model) {
        preLoad(model);
        List<Category> categories = categoryService.list(new QueryWrapper<Category>()
                .orderByDesc("update_time")
                .last("limit 5"));
        List<KeyValue<Integer, Long>> keyValues = articleService.countByCategory(categories.stream().map(Category::getId).collect(Collectors.toList()));
        Map<Integer, Long> categoryMap = keyValues.stream().collect(Collectors.toMap(KeyValue::getKey, KeyValue::getValue));
        model.addAttribute("categories", categories.stream().map(item -> {
            CategoryView view = new CategoryView();
            view.setId(item.getId());
            view.setName(item.getName());
            view.setCount(categoryMap.containsKey(item.getId()) ? categoryMap.get(item.getId()) : 0);
            return view;
        }).collect(Collectors.toList()));
        return "category";
    }

    @GetMapping("category/{id}.html")
    public String categoryItem(@PathVariable String id, @NameConvertModel PageParam pageParam, Model model) {
        preLoad(model);
        Category category = categoryService.getById(id);
        if (category == null) {
            return "error/404";
        }
        Page<Article> articles = articleService.page(new Page<Article>(pageParam.getPageNum(), pageParam.getPageSize()),
                new QueryWrapper<Article>().eq("category_id", category.getId()));
        model.addAttribute("category", category);
        model.addAttribute("articles", articles);
        return "category_item";
    }

    @GetMapping("archive.html")
    public String archive(Model model) {
        preLoad(model);
        return "archive";
    }

    @GetMapping("link.html")
    public String link(Model model) {
        preLoad(model);
        return "link";
    }

    @GetMapping("message.html")
    public String message(
            Model model,
            @NameConvertModel PageParam pageParam
    ) {
        preLoad(model);
        Page<Message> page = messageService.page(
                new Page<>(pageParam.getPageNum(), pageParam.getPageNum()),
                new QueryWrapper<Message>().eq("type", MessageTypeEnum.BLOG.getValue())
        );
        model.addAttribute("page", page);
        return "message";
    }

    @GetMapping("about.html")
    public String about(Model model) {
        preLoad(model);
        return "about";
    }

    @GetMapping("*")
    public String to404(Model model) {
        preLoad(model);
        return "error/404";
    }

}
