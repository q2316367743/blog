package xyz.esion.blog;

import cn.hutool.system.SystemUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

/**
 * @author Esion
 * @since 2021/6/20
 */
@SpringBootApplication
public class BlogApplication {

    public static String ROOT_PATH;

    public static void main(String[] args) {
        // 上传文件根目录
        if (args.length == 0){
            ROOT_PATH = SystemUtil.get("user.home") + File.separator + "blog";
            File file = new File(ROOT_PATH);
            if (!file.exists()){
                file.mkdir();
            }
        }else {
            ROOT_PATH = args[0];
            File file = new File(ROOT_PATH);
            if (!file.exists()){
                file.mkdirs();
            }
        }
        SpringApplication.run(BlogApplication.class, args);
    }

}
