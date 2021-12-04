package xyz.esion.blog.controller.manage;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.entity.Dict;
import xyz.esion.blog.global.KeyValue;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.DictService;
import xyz.esion.blog.view.DictView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Esion
 * @since 2021/12/4
 */
@RestController
@RequestMapping("manage/api/dict")
@RequiredArgsConstructor
public class DictController {

    private final DictService dictService;

    @GetMapping("{type}/list")
    public Result<List<DictView>> list(@PathVariable Integer type) {
        return Result.success(dictService
                .list(new QueryWrapper<Dict>().eq("type", type)
                        .orderByDesc("create_time"))
                .stream()
                .map(item -> {
                    DictView view = new DictView();
                    view.setId(item.getId());
                    view.setKey(item.getItemKey());
                    view.setValue(item.getItemValue());
                    return view;
                })
                .collect(Collectors.toList()));
    }

    @GetMapping("{type}/map")
    public Result<Map<String, String>> map(@PathVariable Integer type) {
        return Result.success(dictService
                .list(new QueryWrapper<Dict>().eq("type", type)
                        .orderByDesc("create_time"))
                .stream()
                .collect(Collectors.toMap(Dict::getItemKey, Dict::getItemValue, (e1, e2) -> e2)));
    }

    @PostMapping("{type}")
    public Result<Boolean> save(
            @PathVariable Integer type,
            @RequestBody KeyValue<String, String> keyValue
    ) {
        Dict dict = new Dict();
        dict.setType(type);
        dict.setItemKey(keyValue.getKey());
        dict.setItemValue(keyValue.getValue());
        try {
            return Result.success(dictService.save(dict));
        } catch (DuplicateKeyException e) {
            return Result.fail("键值重复");
        }
    }

    @PutMapping("{id}")
    public Result<Boolean> update(
            @PathVariable Integer id,
            @RequestBody KeyValue<String, String> keyValue
    ) {
        Dict dict = new Dict();
        dict.setId(id);
        dict.setItemKey(keyValue.getKey());
        dict.setItemValue(keyValue.getValue());
        try {
            return Result.success(dictService.updateById(dict));
        } catch (DuplicateKeyException e) {
            return Result.fail("键值重复");
        }
    }

    @DeleteMapping("{id}")
    public Result<Boolean> remove(@PathVariable Integer id) {
        return Result.success(dictService.removeById(id));
    }

}
