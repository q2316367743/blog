package xyz.esion.blog.controller.manage;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.domain.Config;
import xyz.esion.blog.entity.Admin;
import xyz.esion.blog.enumeration.LoginStatusEnum;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.ConfigService;
import xyz.esion.blog.view.LoginView;
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
    private final ConfigService configService;

    @GetMapping("login")
    public Result<LoginView> login(String username, String password) {
        Admin admin = adminService.getOne(new QueryWrapper<Admin>()
                .eq("username", username)
                .eq("password", new Digester(DigestAlgorithm.MD5).digestHex(password)));
        if (admin != null) {
            Config config = configService.info();
            // 登陆成功
            StpUtil.setLoginId(admin.getId());
            return Result.success(new LoginView(StpUtil.getTokenValue(), false, config.getHref()));
        }else {
            return Result.fail();
        }
    }

    @GetMapping("info")
    public Result<LoginView> info() {
        Config config = configService.info();
        LoginView view = new LoginView();
        view.setWebsite(config.getHref());
        return Result.success(view);
    }

    @PutMapping("update")
    public Result<Boolean> update(@RequestBody Admin admin) {
        admin.setId(StpUtil.getLoginIdAsInt());
        int level = 0;
        if (StrUtil.isBlank(admin.getUsername())) {
            admin.setUsername(null);
            level++;
        }
        if (StrUtil.isBlank(admin.getPassword())) {
            admin.setPassword(null);
            level++;
        }else {
            admin.setPassword(new Digester(DigestAlgorithm.MD5).digestHex(admin.getPassword()));
        }
        if (level == 2) {
            return Result.success();
        }
        boolean update = adminService.updateById(admin);
        if (update) {
            StpUtil.logout();
        }
        return Result.success(update);
    }

    @GetMapping("logout")
    public Result<Boolean> logout() {
        StpUtil.logout();
        return Result.success(true);
    }

}
