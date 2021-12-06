package xyz.esion.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
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
 * @author Esion
 * @since 2021/11/17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Override
    public List<Tree<Integer>> tree() {
        List<Menu> menus = this.baseMapper.selectList(new QueryWrapper<Menu>()
                .orderByAsc("sequence")
                .orderByDesc("create_time"));
        List<TreeNode<Integer>> nodes = new LinkedList<>();
        for (Menu menu : menus) {
            TreeNode<Integer> node = new TreeNode<>();
            node.setId(menu.getId());
            node.setName(menu.getName());
            node.setParentId(menu.getPId());
            node.setExtra(Dict.create().set("data", menu));
            nodes.add(node);
        }
        return TreeUtil.build(nodes);
    }


}




