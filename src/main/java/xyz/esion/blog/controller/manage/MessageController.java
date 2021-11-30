package xyz.esion.blog.controller.manage;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.condition.MessageCondition;
import xyz.esion.blog.entity.Message;
import xyz.esion.blog.global.NameConvertModel;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.param.MessageParam;
import xyz.esion.blog.param.PageParam;
import xyz.esion.blog.service.MessageService;
import xyz.esion.blog.view.PageView;

/**
 * @author Esion
 * @since 2021/11/27
 */
@RestController
@RequestMapping("manage/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public Result<PageView<Message>> page(
            @NameConvertModel PageParam pageParam,
            @NameConvertModel MessageCondition condition
    ) {
        Page<Message> messagePage = messageService.page(
                new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                new QueryWrapper<Message>()
                        .eq(condition.getType() != null, "type", condition.getType())
                        .like(StrUtil.isNotBlank(condition.getName()), "name", condition.getName()));
        return Result.success(new PageView<>(
                messagePage.getCurrent(),
                messagePage.getSize(),
                messagePage.getPages(),
                messagePage.getTotal(),
                messagePage.getRecords()
        ));
    }

    @PostMapping
    public Result<Boolean> save(@RequestBody MessageParam param) {
        if (param.getType() == null) {
            throw new IllegalArgumentException("类型不能为空");
        }
        if (StrUtil.isBlank(param.getEmail())) {
            throw new IllegalArgumentException("邮箱不能为空");
        }
        param.setId(null);
        if (StrUtil.isBlank(param.getName())) {
            throw new IllegalArgumentException("昵称不能为空");
        }
        if (StrUtil.isBlank(param.getContent())) {
            throw new IllegalArgumentException("内容不能为空");
        }
        Message message = BeanUtil.copyProperties(param, Message.class);
        return Result.success(messageService.save(message));
    }

    @PutMapping("{id}")
    public Result<Boolean> update(
            @PathVariable Integer id,
            @RequestBody MessageParam param
    ) {
        if (param.getType() == null) {
            throw new IllegalArgumentException("类型不能为空");
        }
        if (StrUtil.isBlank(param.getEmail())) {
            throw new IllegalArgumentException("邮箱不能为空");
        }
        param.setId(id);
        if (StrUtil.isBlank(param.getName())) {
            throw new IllegalArgumentException("昵称不能为空");
        }
        if (StrUtil.isBlank(param.getContent())) {
            throw new IllegalArgumentException("内容不能为空");
        }
        Message message = BeanUtil.copyProperties(param, Message.class);
        return Result.success(messageService.updateById(message));
    }

    @DeleteMapping("{id}")
    public Result<Boolean> remove(@PathVariable Integer id) {
        return Result.success(messageService.removeById(id));
    }

}
