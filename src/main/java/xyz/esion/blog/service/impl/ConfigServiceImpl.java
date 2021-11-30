package xyz.esion.blog.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;
import xyz.esion.blog.constant.BackupConstant;
import xyz.esion.blog.domain.Config;
import xyz.esion.blog.service.ConfigService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Esion
 * @since 2021/11/20
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    private Config config;

    @PostConstruct
    public void init() {
        File file = new File(BackupConstant.CONFIG_PATH);
        if (file.exists()) {
            this.config = JSONUtil.toBean(FileUtil.readString(file, StandardCharsets.UTF_8), Config.class);
        }else {
            Config config = new Config();
            config.setHref("http://localhost:8990");
            config.setName("云落天都的博客");
            config.setKeywords(Arrays.asList("云落天都", "博客", "网络日志"));
            config.setFavicon("/favicon.ico");
            config.setBackground("https://static.zkqiang.cn/images/20191231163321.jpg-slim");
            config.setPageBackground("https://img-blog.csdnimg.cn/img_convert/cce6857f9276c2ba78d3f4b9af3b036f.png");
            config.setArticleBackground("https://img-blog.csdnimg.cn/img_convert/cce6857f9276c2ba78d3f4b9af3b036f.png");
            config.setCategoryBackground("https://img-blog.csdnimg.cn/img_convert/cce6857f9276c2ba78d3f4b9af3b036f.png");
            config.setArchiveBackground("https://img-blog.csdnimg.cn/img_convert/cce6857f9276c2ba78d3f4b9af3b036f.png");
            config.setLinkBackground("https://img-blog.csdnimg.cn/img_convert/cce6857f9276c2ba78d3f4b9af3b036f.png");
            config.setAboutBackground("https://img-blog.csdnimg.cn/img_convert/cce6857f9276c2ba78d3f4b9af3b036f.png");
            config.setKeepOnRecord("鄂ICP备2021003611号-1");
            config.setIndex(new Config.Index(
                    "云落天都的博客",
                    "云落天都的博客描述",
                    Arrays.asList("真正的大师永远怀着一颗学徒的心。",
                            "任何时候都不要被骄傲蒙蔽了双眼！",
                            "绝对不要坐等胜利的到来。",
                            "愤怒，带来冲动。"),
                    150,
                    true
            ));
            config.setMusic(new LinkedList<>());
            this.config = config;
            FileUtil.writeString(JSONUtil.toJsonStr(this.config), new File(BackupConstant.CONFIG_PATH), StandardCharsets.UTF_8);
        }
    }

    @Override
    public Config info() {
        return this.config;
    }

    @Override
    public void update(Config config) {
        this.config = config;
        FileUtil.writeString(JSONUtil.toJsonStr(this.config), new File(BackupConstant.CONFIG_PATH), StandardCharsets.UTF_8);
    }

}
