package xyz.esion.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>作者信息</p>
 * <p>全局</p>
 *
 * @author qiao shengda
 * @since 2021/6/20
 */
@Data
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 描述
     */
    private String description;

    /**
     * 基本信息
     *
     * */
    private String content;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 原始内容
     */
    private String originalContent;

    /**
     * 其他社交地址
     */
    private List<Account> accounts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Account implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 名称
         */
        private String name;
        /**
         * 图标
         */
        private String icon;
        /**
         * 链接
         */
        private String href;
        /**
         * 模式，1：链接，2：图片
         */
        private Integer mode;

    }

}
