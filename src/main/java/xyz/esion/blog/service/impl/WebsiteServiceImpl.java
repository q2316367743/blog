package xyz.esion.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.esion.blog.constant.FileConstant;
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
     * 博客首次运行时间
     */
    private Date initDateTime;
    private Website website;

    @PostConstruct
    public void init() {
        File file = new File(FileConstant.INIT_DATE_TIME);
        if (file.exists()) {
            try {
                this.initDateTime = DateUtil.parseDateTime(FileUtil.readString(file, StandardCharsets.UTF_8));
            }catch (Exception e) {
                // 时间格式错误
                this.initDateTime = new Date();
                FileUtil.writeString(DateUtil.format(this.initDateTime, "yyyy-MM-dd HH:mm:ss"), file, StandardCharsets.UTF_8);
            }
        }else {
            this.initDateTime = new Date();
            FileUtil.writeString(DateUtil.format(this.initDateTime, "yyyy-MM-dd HH:mm:ss"), file, StandardCharsets.UTF_8);
        }
    }

    @Override
    public Website info() {
        return this.website;
    }

    @Override
    public void sync() {
        Website website = new Website();
        website.setArticleCount(articleListMapper.selectCount(new QueryWrapper<>()));
        website.setCategoryCount(categoryListMapper.selectCount(new QueryWrapper<>()));
        website.setRunTime(Math.toIntExact(DateUtil.betweenDay(this.initDateTime, new Date(), false)));
        website.setWordCount(0L);
        website.setAccessCount(0);
        Article article = articleListMapper.selectOne(new QueryWrapper<Article>().last("limit 1"));
        if (article != null) {
            website.setLastUpdate(article.getUpdateTime());
        }
        this.website = website;
    }

    @Override
    public void accountCountIncrement() {
        this.website.setAccessCount(this.website.getAccessCount() + 1);
    }

}
