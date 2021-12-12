package xyz.esion.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.esion.blog.entity.Admin;
import xyz.esion.blog.service.AdminService;
import xyz.esion.blog.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
 * @author Esion
 * @since 2021/12/12
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




