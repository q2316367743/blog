package xyz.esion.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Service;
import xyz.esion.blog.global.Author;
import xyz.esion.blog.service.AuthorService;

/**
 * @author qiao shengda
 * @since 2021/7/11
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private Author author;

    @Override
    public Author getAll() {
        return author;
    }

    @Override
    public Author getBase() {
        return BeanUtil.copyProperties(author, Author.class, "baseInfo");
    }

    @Override
    public Author getOther() {
        Author author = new Author();
        author.setName(this.author.getName());
        author.setBaseInfo(this.author.getBaseInfo());
        return author;
    }

    @Override
    public boolean update(Author author) {
        if (ObjectUtil.isNotEmpty(author.getName())){
            this.author.setName(author.getName());
        }
        if (ObjectUtil.isNotEmpty(author.getAvatar())){
            this.author.setAvatar(author.getAvatar());
        }
        if (ObjectUtil.isNotEmpty(author.getDescription())){
            this.author.setDescription(author.getDescription());
        }
        if (ObjectUtil.isNotEmpty(author.getGitee())){
            this.author.setGitee(author.getGitee());
        }
        if (ObjectUtil.isNotNull(author.getOther()) && !author.getOther().isEmpty()){
            this.author.setOther(author.getOther());
        }
        if (ObjectUtil.isNotEmpty(author.getBaseInfo())){
            this.author.setBaseInfo(author.getBaseInfo());
        }
        return true;
    }

    public AuthorServiceImpl(Author author) {
        this.author = author;
    }
}
