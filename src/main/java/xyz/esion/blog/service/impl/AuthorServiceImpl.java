package xyz.esion.blog.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;
import xyz.esion.blog.constant.BackupConstant;
import xyz.esion.blog.domain.Author;
import xyz.esion.blog.enumeration.EditorTypeEnum;
import xyz.esion.blog.service.AuthorService;

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
public class AuthorServiceImpl implements AuthorService {

    private Author author;

    @PostConstruct
    public void init() {
        // 查询是否有备份
        File file = new File(BackupConstant.AUTHOR_PATH);
        if (file.exists()) {
            String authorJson = FileUtil.readString(file, StandardCharsets.UTF_8);
            this.author = JSONUtil.toBean(authorJson, Author.class);
        }else {
            Author author = new Author();
            author.setAvatar("https://portrait.gitee.com/uploads/avatars/user/1786/5358547_qiaoshengda_1578985319.png");
            author.setName("云落天都");
            author.setTags(Arrays.asList("后端开发", "前端开发", "游戏爱好者"));
            author.setDescription("我的个人博客");
            author.setContent("<p>无</p>");
            author.setType(EditorTypeEnum.MARKDOWN.getValue());
            author.setOriginalContent("无");
            LinkedList<Author.Account> accounts = new LinkedList<>();
            accounts.add(new Author.Account("github", "iconfont icon-github-fill", "https://github.com/q2316367743", 1));
            accounts.add(new Author.Account("gitee", "iconfont icon-gitee-fill", "https://gitee.com/qiaoshengda", 1));
            author.setAccounts(accounts);
            this.author = author;
            // 如果不存在，则默认值写入
            FileUtil.writeString(JSONUtil.toJsonStr(this.author), new File(BackupConstant.AUTHOR_PATH), StandardCharsets.UTF_8);
        }
    }

    @Override
    public Author info() {
        return this.author;
    }

    @Override
    public void update(Author author) {
        this.author = author;
        // 更新重新写入
        FileUtil.writeString(JSONUtil.toJsonStr(this.author), new File(BackupConstant.AUTHOR_PATH), StandardCharsets.UTF_8);
    }

}
