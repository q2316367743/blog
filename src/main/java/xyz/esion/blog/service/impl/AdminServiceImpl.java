package xyz.esion.blog.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.esion.blog.constant.FileConstant;
import xyz.esion.blog.domain.Admin;
import xyz.esion.blog.enumeration.LoginStatusEnum;
import xyz.esion.blog.service.AdminService;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

/**
 * @author Esion
 * @since 2021/11/20
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private Admin admin;

    @PostConstruct
    public void init() {
        // 初始化随机一个密码
//        String password = RandomUtil.randomString(12);
        String password = "111111";
        // 写入文件
        FileUtil.writeString(new JSONObject().set("username", "admin").set("password", password).toJSONString(4),
                FileConstant.PASSWORD_PATH, StandardCharsets.UTF_8);
        log.debug("\n初始账户：admin\n初始密码：{}\n注意修改", password);
        log.debug("初始密码存放在：{}，注意修改", FileConstant.PASSWORD_PATH);
        // 加密存储
        this.admin = new Admin("admin", new Digester(DigestAlgorithm.MD5).digestHex(password));
    }

    @Override
    public LoginStatusEnum login(String username, String password, String mfa) {
        password = new Digester(DigestAlgorithm.MD5).digestHex(password);
        if (this.admin.getUsername().equals(username) && this.admin.getPassword().equals(password)) {
            return LoginStatusEnum.SUCCESS;
        }
        return LoginStatusEnum.USERNAME_OR_PASSWORD_ERROR;
    }

}
