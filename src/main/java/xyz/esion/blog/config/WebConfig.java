package xyz.esion.blog.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.esion.blog.enumeration.ResultCodeEnum;
import xyz.esion.blog.global.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qiao shengda
 * @since 2021/6/21
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final static Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 访问量计算
        // 权限拦截
        String OPTIONS = "OPTIONS";
        registry.addInterceptor(new HandlerInterceptor(){
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                if (OPTIONS.equals(request.getMethod())){
                    return true;
                }else {
                    try {
                        StpUtil.checkLogin();
                    }catch (NotLoginException exception){
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("text/json;charset=UTF-8 ");
                        if(exception.getType().equals(NotLoginException.NOT_TOKEN)) {
                            try {
                                response.getWriter().print(JSONUtil.toJsonStr(Result.fail(ResultCodeEnum.UN_AUTHENTICATION)));
                                return false;
                            } catch (IOException ignored) {
                            }
                        }
                        else if(exception.getType().equals(NotLoginException.INVALID_TOKEN) || exception.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
                            try {
                                response.getWriter().print(JSONUtil.toJsonStr(Result.fail(ResultCodeEnum.INVALID)));
                                return false;
                            } catch (IOException ignored) {
                            }
                        }
                    }
                }


                // 通过验证
                return true;
            }
        })
                .addPathPatterns("/manage/**")
                .excludePathPatterns("/manage/admin/login");
    }

}
