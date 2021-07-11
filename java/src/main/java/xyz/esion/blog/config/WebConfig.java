package xyz.esion.blog.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.servlet.model.SaRequestForServlet;
import cn.dev33.satoken.servlet.model.SaResponseForServlet;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.esion.blog.global.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
                                response.getWriter().print(JSONUtil.toJsonStr(Result.fail(Result.ResultCode.UN_AUTHENTICATION)));
                                return false;
                            } catch (IOException ignored) {
                            }
                        }
                        else if(exception.getType().equals(NotLoginException.INVALID_TOKEN) || exception.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
                            try {
                                response.getWriter().print(JSONUtil.toJsonStr(Result.fail(Result.ResultCode.INVALID)));
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

    @Bean
    public Author author(){
        Author author = new Author();
        author.setName("云落天都");
        author.setDescription("主要是Java技术栈，略带部分源码，随笔");
        author.setAvatar("https://portrait.gitee.com/uploads/avatars/user/1786/5358547_qiaoshengda_1578985319.png!avatar200");
        author.setGitee("https://gitee.com/qiaoshengda");
        List<Author.Other> others = new LinkedList<>();
        others.add(new Author.Other("CSDN", "https://g.csdnimg.cn/static/logo/favicon32.ico", "https://blog.csdn.net/q2316367743"));
        author.setOther(others);
        author.setBaseInfo("<h1>基本信息</h1>\n" +
                "\t\t\t\t\t<p>\uD83D\uDC68基本情况：大四在读，计算机科学与技术专业。</p>\n" +
                "\t\t\t\t\t<p>\n" +
                "\t\t\t\t\t\t\uD83C\uDFAB昵称：云落天都。云落天都，月出九皋。\n" +
                "\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t<p>\uD83C\uDF0D地址：目前在湖北孝感。</p>\n" +
                "\t\t\t\t\t<p>\n" +
                "\t\t\t\t\t\t\uD83D\uDCE7邮箱：<a href=\"mailto:m17762618644@163.com\"\n" +
                "\t\t\t\t\t\t\t>m17762618644@163.com</a\n" +
                "\t\t\t\t\t\t>\n" +
                "\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t<p>\uD83D\uDD28技术方向：主Java，会考虑学一点前端和大数据。</p>\n" +
                "\t\t\t\t\t<p>\uD83D\uDC93兴趣爱好：游戏，编程。</p>\n" +
                "\t\t\t\t\t<p>\uD83D\uDCDD写作风格：一般以学习笔记为主，少量的心得感悟。</p>");
        return author;
    }

    @Bean
    public Config config(){
        Config config = new Config();
        config.setBackground("background.jpg");
        return config;
    }

}
