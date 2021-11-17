package xyz.esion.blog.module.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.esion.blog.entity.Category;
import xyz.esion.blog.entity.Notice;
import xyz.esion.blog.global.*;
import xyz.esion.blog.module.portal.service.ArticleService;
import xyz.esion.blog.module.portal.service.CategoryService;
import xyz.esion.blog.module.portal.service.NoticeService;
import xyz.esion.blog.module.portal.view.ArticleListView;
import xyz.esion.blog.module.portal.view.CategoryView;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.view.PageView;

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

    private final Author author;
    private final Config config;
    private final Website website;

    /**
     * 前置加载项，所有请求都会加载
     */
    private void preLoad(Model model) {
        model.addAttribute("author", author);
        model.addAttribute("config", config);
        model.addAttribute("website", website);
    }

    @GetMapping
    public String index(@NameConvertModel PageParam pageParam, Model model) {
        preLoad(model);
        PageView<ArticleListView> page = articleService.page(pageParam);
        model.addAttribute("page", page);
        return "index";
    }

    @GetMapping("index.html")
    public String home(@NameConvertModel PageParam pageParam, Model model) {
        return index(pageParam, model);
    }

    @GetMapping("category")
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

}
