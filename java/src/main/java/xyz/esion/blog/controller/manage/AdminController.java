package xyz.esion.blog.controller.manage;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.global.Admin;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.AdminService;

/**
 * @author Esion
 * @since 2021/7/11
 */
@RestController
@RequestMapping("manage/admin")
public class AdminController {

    private AdminService adminService;

    @GetMapping("login")
    public Result login(String username, String password){
        boolean login = adminService.login(username, password);
        if (login){
            StpUtil.setLoginId(username);
            return Result.success().data("item", StpUtil.getTokenValue());
        }
        return Result.fail();
    }

    @PutMapping("update")
    public Result update(@RequestBody Admin admin){
        boolean update = adminService.update(admin);
        if (update){
            StpUtil.logout();
        }
        return Result.success();
    }

    @DeleteMapping("logout")
    public Result logout(){
        StpUtil.logout();
        return Result.success();
    }

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
}
