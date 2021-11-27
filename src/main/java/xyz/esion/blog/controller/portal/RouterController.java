package xyz.esion.blog.controller.portal;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.esion.blog.entity.Article;
import xyz.esion.blog.entity.*;
import xyz.esion.blog.enumeration.ArticleStatusEnum;
import xyz.esion.blog.enumeration.LinkStatusEnum;
import xyz.esion.blog.enumeration.MessageTypeEnum;
import xyz.esion.blog.global.*;
import xyz.esion.blog.view.*;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.service.*;

import java.util.*;
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
    private final LinkService linkService;

    private final AuthorService authorService;
    private final ConfigService configService;
    private final WebsiteService websiteService;

    /**
     * 前置加载项，所有请求都会加载
     */
    @ModelAttribute
    private void preLoad(Model model) {
        model.addAttribute("author", authorService.info());
        model.addAttribute("config", configService.info());
        model.addAttribute("website", websiteService.info());
        model.addAttribute("menus", menuService.tree());
    }

    @GetMapping
    public String index(@NameConvertModel PageParam pageParam, Model model) {
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
        // 获取全部分类
        List<Category> categories = categoryService.list(new QueryWrapper<Category>()
                .orderByDesc("update_time"));
        // 获取每个分类的数量
        List<KeyValue<Long, Long>> keyValues = articleService.countByCategory(categories.stream().map(Category::getId).collect(Collectors.toList()));
        Map<Long, Long> categoryMap = keyValues.stream().collect(Collectors.toMap(KeyValue::getKey, KeyValue::getValue));
        model.addAttribute("categories", categories.stream().map(item -> {
            CategoryView view = new CategoryView();
            view.setId(item.getId());
            view.setName(item.getName());
            view.setCount(categoryMap.containsKey(item.getId().longValue()) ? categoryMap.get(item.getId().longValue()) : 0);
            if (view.getCount() > 0) {
                // 获取这个分类下前十篇文章，id+名称
                List<Article> articles = articleService.list(new QueryWrapper<Article>()
                        .eq("category_id", view.getId())
                        .eq("status", ArticleStatusEnum.RELEASE.getValue())
                        .last("limit 10"));
                view.setArticles(articles
                        .stream()
                        .map(article -> new CategoryView.Article(article.getId(), article.getTitle()))
                        .collect(Collectors.toList()));
            } else {
                view.setArticles(new LinkedList<>());
            }
            return view;
        }).collect(Collectors.toList()));
        return "category";
    }

    @GetMapping("category/{id}.html")
    public String categoryItem(@PathVariable String id, @NameConvertModel PageParam pageParam, Model model) {
        Category category = categoryService.getById(id);
        if (category == null) {
            return "error/404";
        }
        Page<Article> articles = articleService.page(new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                new QueryWrapper<Article>()
                        .eq("category_id", category.getId())
                        .eq("status", ArticleStatusEnum.RELEASE.getValue()));

        model.addAttribute("category", category);
        model.addAttribute("page", buildArticleView(articles));
        return "category_item";
    }

    @GetMapping("archive.html")
    public String archive(@NameConvertModel PageParam pageParam, Model model) {
        Page<Article> articles = articleService.page(new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                new QueryWrapper<Article>().eq("status", ArticleStatusEnum.RELEASE.getValue()));
        model.addAttribute("page", buildArticleView(articles));
        return "archive";
    }

    @GetMapping("link.html")
    public String link(Model model) {
        List<Link> links = linkService.list(new QueryWrapper<Link>()
                .eq("status", LinkStatusEnum.PASS.getValue()));
        model.addAttribute("links", links);
        return "link";
    }

    @GetMapping("message.html")
    public String message(
            Model model,
            @NameConvertModel PageParam pageParam
    ) {
        Page<Message> page = messageService.page(
                new Page<>(pageParam.getPageNum(), pageParam.getPageNum()),
                new QueryWrapper<Message>().eq("type", MessageTypeEnum.BLOG.getValue())
        );
        model.addAttribute("page", page);
        return "message";
    }

    @GetMapping("about.html")
    public String about() {
        return "about";
    }

    @GetMapping("*")
    public String to404() {
        return "error/404";
    }

    private PageView<ArticleCategoryListView> buildArticleView(Page<Article> articlePage) {
        // 对文章进行处理，年做分组
        Map<String, List<ArticleCategoryListView.Item>> articleMap = articlePage.getRecords().stream().map(article -> {
            DateTime updateTime = new DateTime(article.getUpdateTime());
            ArticleCategoryListView.Item item = new ArticleCategoryListView.Item();
            item.setId(article.getId());
            item.setTitle(article.getTitle());
            item.setYear(updateTime.toString("yyyy"));
            item.setMonth(updateTime.toString("MM"));
            item.setDay(updateTime.toString("dd"));
            return item;
        }).collect(Collectors.groupingBy(ArticleCategoryListView.Item::getYear));
        List<ArticleCategoryListView> views = new LinkedList<>();
        // 构建视图
        for (Map.Entry<String, List<ArticleCategoryListView.Item>> entry : articleMap.entrySet()) {
            ArticleCategoryListView view = new ArticleCategoryListView();
            view.setYear(entry.getKey());
            // 月日做排序
            entry.getValue().sort((o1, o2) -> {
                int monthSort = StrUtil.compare(o2.getMonth(), o1.getMonth(), false);
                if (monthSort == 0) {
                    return StrUtil.compare(o2.getDay(), o1.getDay(), false);
                } else {
                    return monthSort;
                }
            });
            view.setItems(entry.getValue());
            views.add(view);
        }
        views.sort((o1, o2) -> StrUtil.compare(o2.getYear(), o1.getYear(), false));
        return new PageView<>(articlePage.getCurrent(),
                articlePage.getSize(),
                articlePage.getPages(),
                articlePage.getTotal(),
                views);
    }

}
