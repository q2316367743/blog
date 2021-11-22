package xyz.esion.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.esion.blog.entity.Message;
import xyz.esion.blog.mapper.MessageMapper;
import xyz.esion.blog.service.MessageService;

/**
 * @author Esion
 * @since 2021/11/22
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
}
