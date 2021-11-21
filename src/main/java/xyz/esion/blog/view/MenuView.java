package xyz.esion.blog.view;

import lombok.Data;

import java.util.List;

/**
 * @author Esion
 * @since 2021/11/17
 */
@Data
public class MenuView {

    private Integer id;

    /**
     * 图标
     */
    private String icon;

    /**
     * 名称
     */
    private String name;

    /**
     * 打开方式：0：当前页面，1：新页面
     */
    private Integer target;

    /**
     * 链接
     */
    private String href;

    /**
     * 子菜单
     */
    private List<MenuView> children;

}
