package xyz.esion.blog.controller.manage;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.esion.blog.global.Constant;
import xyz.esion.blog.global.Result;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author qiao shengda
 * @since 2021/7/11
 */
@RestController
@RequestMapping("manage/upload")
public class UploadController {

    @PostMapping("music")
    public Result music(@RequestBody MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String name = Long.toString(System.currentTimeMillis());
        if (ObjectUtil.isNotEmpty(originalFilename)){
            String[] split = originalFilename.split("\\.");
            name = name + '.' + split[split.length - 1];
        }
        try {
            IoUtil.copy(file.getInputStream(), new FileOutputStream(Constant.MUSIC_PATH + name));
            return Result.success().data("item", name);
        } catch (IOException e) {
            return Result.fail();
        }
    }

    @PostMapping("image")
    public Result image(@RequestBody MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String name = Long.toString(System.currentTimeMillis());
        if (ObjectUtil.isNotEmpty(originalFilename)){
            String[] split = originalFilename.split("\\.");
            name = name + '.' + split[split.length - 1];
        }
        try {
            IoUtil.copy(file.getInputStream(), new FileOutputStream(Constant.IMAGE_PATH + name));
            return Result.success().data("item", name);
        } catch (IOException e) {
            return Result.fail();
        }
    }

}
