package xyz.esion.blog;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.system.SystemUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import xyz.esion.blog.global.Admin;

import java.io.File;

/**
 * @author Esion
 * @since 2021/6/20
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {Admin.class})
public class BlogApplication {

    public static String ROOT_PATH;

    public static void main(String[] args) {
        // 上传文件根目录
        if (args.length == 0){
            ROOT_PATH = SystemUtil.get("user.home") + File.separator + "blog";
            String path = ArrayUtil.join(new String[]{"assets", "image", "blog"}, File.separator);
            File file = new File(ROOT_PATH + File.separator + path);
            if (!file.exists()){
                boolean mkdirs = file.mkdirs();
                if (!mkdirs){
                    Console.error("默认文件夹创建失败，请检查权限");
                }
            }
        }else {
            ROOT_PATH = args[0];
            File file = new File(ROOT_PATH);
            if (!file.exists()){
                Console.error("指定文件夹文件夹不存在，准备创建");
                boolean mkdirs = file.mkdirs();
                if (!mkdirs){
                    Console.error("指定文件夹文件夹创建失败，请检查权限");
                }
            }
        }
        SpringApplication.run(BlogApplication.class, args);
    }

}
