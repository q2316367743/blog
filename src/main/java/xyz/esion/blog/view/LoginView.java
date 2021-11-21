package xyz.esion.blog.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/11/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Token
     */
    private String token;

    /**
     * 是否需要mfa认证
     */
    private Boolean needMfa;

}
