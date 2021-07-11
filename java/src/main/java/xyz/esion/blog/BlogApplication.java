package xyz.esion.blog;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.system.SystemUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import xyz.esion.blog.global.Admin;
import xyz.esion.blog.global.Constant;

import java.io.File;

/**
 * @author Esion
 * @since 2021/6/20
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {Admin.class})
public class BlogApplication {

    public static void main(String[] args) {
        // 上传文件根目录
        if (args.length == 0){
            Constant.ROOT_PATH = SystemUtil.get("user.home") + File.separator + "blog";
        }else {
            Constant.ROOT_PATH = args[0];
        }
        Constant.IMAGE_PATH = Constant.ROOT_PATH + File.separator + ArrayUtil.join(new String[]{"assets", "blog", "image", ""}, File.separator);
        Constant.MUSIC_PATH = Constant.ROOT_PATH + File.separator + ArrayUtil.join(new String[]{"assets", "blog", "music", ""}, File.separator);
        File image = new File(Constant.IMAGE_PATH);
        File music = new File(Constant.MUSIC_PATH);
        if (!image.exists()){
            image.mkdirs();
        }
        if (!music.exists()){
            music.mkdirs();
        }
        SpringApplication.run(BlogApplication.class, args);
    }

}
