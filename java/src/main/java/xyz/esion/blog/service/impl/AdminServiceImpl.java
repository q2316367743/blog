package xyz.esion.blog.service.impl;

import org.springframework.stereotype.Service;
import xyz.esion.blog.global.Admin;
import xyz.esion.blog.service.AdminService;

/**
 * @author Esion
 * @since 2021/7/11
 */
@Service
public class AdminServiceImpl implements AdminService {

    private Admin admin;

    @Override
    public boolean login(String username, String password) {
        return this.admin.getUsername().equals(username) && this.admin.getPassword().equals(password);
    }

    @Override
    public boolean update(Admin admin) {
        this.admin.setUsername(admin.getUsername());
        this.admin.setPassword(admin.getPassword());
        return true;
    }


    public AdminServiceImpl(Admin admin) {
        this.admin = admin;
    }
}
