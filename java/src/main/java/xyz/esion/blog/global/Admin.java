package xyz.esion.blog.global;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 管理员信息
 *
 * @author Esion
 * @since 2021/7/11
 */
@ConfigurationProperties(prefix = "admin")
@Component
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     * */
    private String username = "Esion";

    /**
     * 密码
     * */
    private String password = "123456";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
