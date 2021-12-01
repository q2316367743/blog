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
                        .eq(condition.getIsRead() != null, "is_read", condition.getIsRead())
                        .like(StrUtil.isNotBlank(condition.getName()), "name", condition.getName())
                        .orderByDesc("create_time"));
        return Result.success(new PageView<>(
                messagePage.getCurrent(),
                messagePage.getSize(),
                messagePage.getPages(),
                messagePage.getTotal(),
                messagePage.getRecords()
        ));
    }

    @PutMapping("{id}")
    public Result<Boolean> update(
            @PathVariable Integer id,
            @RequestBody Message param
    ) {
        Message message = new Message();
        message.setId(id);
        message.setIsRead(param.getIsRead());
        return Result.success(messageService.updateById(message));
    }

}
