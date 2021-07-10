package xyz.esion.blog;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.system.SystemUtil;

import java.io.File;

/**
 * 图片资源服务器，方便Windows上没有nginx服务器进行调试
 *
 * @author Esion
 * @since 2021/7/4
 */
public class ImageServer {

    private static final String ROOT_PATH;

    static {
        ROOT_PATH = SystemUtil.get("user.home") + File.separator + "blog";
        String path = ArrayUtil.join(new String[]{"assets", "image", "blog"}, File.separator);
        File file = new File(ROOT_PATH + File.separator + path);
        if (!file.exists()){
            boolean mkdirs = file.mkdirs();
            if (!mkdirs){
                Console.error("文件夹创建失败，请检查权限");
            }
        }
    }

    public static void main(String[] args) {
        Console.log(args);
        HttpUtil.createServer(80)
                // 设置默认根目录
                .setRoot(ROOT_PATH)
                .start();
    }

}
