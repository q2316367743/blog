package xyz.esion.blog.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import xyz.esion.blog.enumeration.ResultCodeEnum;
import xyz.esion.blog.global.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author qiao shengda
 * @since 2021/6/21
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final static Logger logger = LoggerFactory.getLogger(WebConfig.class);
    private final static String OPTIONS = "OPTIONS";

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
        registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                        if (OPTIONS.equals(request.getMethod())) {
                            return true;
                        } else {
                            try {
                                StpUtil.checkLogin();
                            } catch (NotLoginException exception) {
                                response.setCharacterEncoding("utf-8");
                                response.setContentType("text/json;charset=UTF-8 ");
                                if (exception.getType().equals(NotLoginException.NOT_TOKEN)) {
                                    try {
                                        response.getWriter().print(JSONUtil.toJsonStr(Result.fail(ResultCodeEnum.UN_AUTHENTICATION)));
                                        return false;
                                    } catch (IOException ignored) {
                                    }
                                } else if (exception.getType().equals(NotLoginException.INVALID_TOKEN) || exception.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
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
                .addPathPatterns("/manage/api/**/*")
                .excludePathPatterns("/manage/api/admin/login");
    }

    /**
     * 模型下划线名称绑定处理器
     */
    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof RequestMappingHandlerAdapter) {
                    RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
                    List<HandlerMethodArgumentResolver> argumentResolvers
                            = new ArrayList<>(Objects.requireNonNull(adapter.getArgumentResolvers()));
                    argumentResolvers.add(0, nameConvertModelProcessor(adapter));
                    adapter.setArgumentResolvers(argumentResolvers);
                }
                return bean;
            }

            private NameConvertModelProcessor nameConvertModelProcessor(RequestMappingHandlerAdapter adapter) {
                NameConvertModelProcessor processor = new NameConvertModelProcessor(adapter);
                processor.setPropertyNamingStrategyBase(new PropertyNamingStrategy.SnakeCaseStrategy());
                return processor;
            }
        };
    }

}
