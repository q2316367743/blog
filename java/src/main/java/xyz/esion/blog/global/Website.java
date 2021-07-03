package xyz.esion.blog.global;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import xyz.esion.blog.entity.ArticleList;
import xyz.esion.blog.mapper.ArticleInfoMapper;
import xyz.esion.blog.mapper.ArticleListMapper;
import xyz.esion.blog.mapper.ArticleMapper;
import xyz.esion.blog.mapper.CategoryListMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 网站数据
 *
 * @author qiao shengda
 * @since 2021/6/22
 */
@Component
public class Website implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章数量
     * */
    private Integer articleCount;
    /**
     * 分类数量
     * */
    private Integer categoryCount;

    /**
     * <p>运行时间</p>
     * <p>单位：天</p>
     * */
    private Integer runTime;

    /**
     * 总字数
     * */
    private Long wordCount;

    /**
     * 访问量
     * */
    private Integer accessCount;

    /**
     * 最后更新时间
     * */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastUpdate;

    @JsonIgnore
    private ArticleListMapper articleListMapper;
    @JsonIgnore
    private ArticleInfoMapper articleInfoMapper;
    @JsonIgnore
    private CategoryListMapper categoryListMapper;
    @JsonIgnore
    private Date now;

    @Autowired
    public Website(ArticleListMapper articleListMapper, ArticleInfoMapper articleInfoMapper, CategoryListMapper categoryListMapper) {
        this.articleListMapper = articleListMapper;
        this.articleInfoMapper = articleInfoMapper;
        this.categoryListMapper = categoryListMapper;
        this.now = new Date();
        this.accessCount = 0;
        this.update();
    }

    /**
     * 数据更新
     * */
    public void update(){
        this.articleCount = articleListMapper.selectCount(new QueryWrapper<>());
        this.categoryCount = categoryListMapper.selectCount(new QueryWrapper<>());
        this.runTime = Math.toIntExact(DateUtil.betweenDay(now, new Date(), false));
        this.wordCount = articleInfoMapper.selectSumWithWordCount() / 1000;
        ArticleList articleList = articleListMapper.selectOne(new QueryWrapper<ArticleList>().last("limit 1"));
        this.lastUpdate = articleList.getUpdateTime();
    }

    /**
     * 访问量+1
     * */
    public void access(){
        this.accessCount += 1;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public Integer getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Integer categoryCount) {
        this.categoryCount = categoryCount;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public Long getWordCount() {
        return wordCount;
    }

    public void setWordCount(Long wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Integer accessCount) {
        this.accessCount = accessCount;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
