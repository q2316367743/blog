package xyz.esion.blog.service;

import xyz.esion.blog.global.Author;

/**
 * @author qiao shengda
 * @since 2021/7/11
 */
public interface AuthorService {

    /**
     * 获取作者信息
     *
     * @return 作者信息
     * */
    Author getAll();

    /**
     * 获取基础信息
     *
     * @return 作者信息
     * */
    Author getBase();

    /**
     * 获取其他信息
     *
     * @return 作者信息
     * */
    Author getOther();

    /**
     * 更新作者信息
     *
     * @param author 作者信息
     * @return 结果
     * */
    boolean update(Author author);

}
