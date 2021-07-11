package xyz.esion.blog.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.esion.blog.global.Constant;
import xyz.esion.blog.global.Website;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qiao shengda
 * @since 2021/6/21
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private Website website;

    private final static Logger logger = LoggerFactory.getLogger(WebConfig.class);

    /**
     * 当前环境
     * */
    @Value("${spring.profiles.active}")
    private String active;
    private final static String PROFILES = "dev";

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
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                website.access();
                return true;
            }
        }).addPathPatterns("/global/config");
        // 权限拦截
        registry.addInterceptor(new SaRouteInterceptor())
                .addPathPatterns("/manage/**")
                .excludePathPatterns("/manage/admin/login");
    }

    @Autowired
    public void setWebsite(Website website) {
        this.website = website;
        // 如果是开发环境，启动图片服务器
        if (active.equals(PROFILES)){
            logger.debug("当前为开发环境，启动静态资源服务器");
            startImageServer();
            logger.debug("启动成功，服务器地址：127.0.0.1");
        }
    }

    public void startImageServer() {
        HttpUtil.createServer(80)
                // 设置默认根目录
                .setRoot(Constant.ROOT_PATH)
                .start();
    }
}
