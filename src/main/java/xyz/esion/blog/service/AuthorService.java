package xyz.esion.blog.service;

import xyz.esion.blog.domain.Author;

/**
 * @author Esion
 * @since 2021/11/20
 */
public interface AuthorService {

    /**
     * 查询作者详情
     *
     * @return 作者详情
     */
    Author info();

}
