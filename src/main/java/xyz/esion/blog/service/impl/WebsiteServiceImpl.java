package xyz.esion.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.esion.blog.constant.BackupConstant;
import xyz.esion.blog.domain.Website;
import xyz.esion.blog.entity.Article;
import xyz.esion.blog.mapper.ArticleMapper;
import xyz.esion.blog.mapper.CategoryMapper;
import xyz.esion.blog.service.WebsiteService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author Esion
 * @since 2021/11/20
 */
@Service
@RequiredArgsConstructor
public class WebsiteServiceImpl implements WebsiteService {

    private final ArticleMapper articleListMapper;
    private final CategoryMapper categoryListMapper;
    /**
     * 建站时间
     */
    private final Date now = new Date();

    private Website website;

    @PostConstruct
    public void init() {
        File file = new File(BackupConstant.WEBSITE_PATH);
        if (file.exists()) {
            this.website = JSONUtil.toBean(FileUtil.readString(file, StandardCharsets.UTF_8), Website.class);
        }else {
            website = new Website();
            update();
        }
    }

    @Override
    public Website info() {
        return website;
    }

    @Override
    public void update() {
        website.setArticleCount(articleListMapper.selectCount(new QueryWrapper<>()));
        website.setCategoryCount(categoryListMapper.selectCount(new QueryWrapper<>()));
        website.setRunTime(Math.toIntExact(DateUtil.betweenDay(now, new Date(), false)));
        website.setWordCount(0L);
        website.setAccessCount(0);
        Article article = articleListMapper.selectOne(new QueryWrapper<Article>().last("limit 1"));
        if (article != null) {
            website.setLastUpdate(article.getUpdateTime());
        }
    }
}
