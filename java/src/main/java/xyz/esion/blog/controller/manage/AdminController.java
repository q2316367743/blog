package xyz.esion.blog.controller.manage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.service.AdminService;

/**
 * @author Esion
 * @since 2021/7/11
 */
@RestController
@RequestMapping("manage/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
}
