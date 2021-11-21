package xyz.esion.blog.service;

import xyz.esion.blog.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.esion.blog.view.MenuView;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Esion
 * @since 2021/11/17
 */
public interface MenuService extends IService<Menu> {

    List<MenuView> MENU_VIEWS = new LinkedList<>();

    /**
     * 查询全部菜单，菜单只支持两级
     *
     * @return 菜单树
     */
    List<MenuView> tree();

}
