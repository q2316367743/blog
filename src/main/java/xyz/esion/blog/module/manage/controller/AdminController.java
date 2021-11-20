package xyz.esion.blog.module.manage.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.enumeration.LoginStatusEnum;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.module.manage.LoginView;
import xyz.esion.blog.service.AdminService;

/**
 * @author Esion
 * @since 2021/11/20
 */
@RestController
@RequestMapping("/manage/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("login")
    public Result<LoginView> login(String username, String password) {
        LoginStatusEnum status = adminService.login(username, password, null);
        if (status.equals(LoginStatusEnum.SUCCESS)) {
            // 登陆成功
            StpUtil.setLoginId(username);
            return Result.success(new LoginView(StpUtil.getTokenValue(), false));
        }else if (status.equals(LoginStatusEnum.NEED_MFA)) {
            return Result.success(new LoginView("", true));
        }else {
            return Result.fail();
        }
    }

}
