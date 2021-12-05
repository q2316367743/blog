package xyz.esion.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.esion.blog.entity.Menu;
import xyz.esion.blog.service.MenuService;
import xyz.esion.blog.mapper.MenuMapper;
import org.springframework.stereotype.Service;
import xyz.esion.blog.view.MenuView;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Esion
 * @since 2021/11/17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @PostConstruct
    public void init() {
        MENU_VIEWS.clear();
        MENU_VIEWS.addAll(sync());
    }

    @Override
    public List<MenuView> tree() {
        return MENU_VIEWS;
    }

    private List<MenuView> sync() {
        List<MenuView> views = new LinkedList<>();
        List<Menu> menus = this.baseMapper.selectList(new QueryWrapper<Menu>()
                .orderByAsc("sequence")
                .orderByDesc("create_time"));
        Iterator<Menu> menuIterator = menus.iterator();
        // 第一遍，获取一级菜单
        while (menuIterator.hasNext()) {
            Menu menu = menuIterator.next();
            if (menu.getPId().equals(0)) {
                MenuView view = BeanUtil.copyProperties(menu, MenuView.class);
                view.setChildren(new LinkedList<>());
                views.add(view);
                menuIterator.remove();
            }
        }
        // 第二遍，获取子节点
        for (Menu menu : menus) {
            for (MenuView view : views) {
                if (view.getId().equals(menu.getPId())) {
                    MenuView temp = BeanUtil.copyProperties(menu, MenuView.class);
                    view.getChildren().add(temp);
                    break;
                }
            }
        }
        return views;
    }

    @Override
    public boolean updateById(Menu entity) {
        boolean result = super.updateById(entity);
        init();
        return result;
    }

    @Override
    public boolean save(Menu entity) {
        boolean result = super.save(entity);
        init();
        return result;
    }

    @Override
    public boolean removeById(Serializable id) {
        boolean result = super.removeById(id);
        init();
        return result;
    }

}




