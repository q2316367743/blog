package xyz.esion.blog.controller;

import cn.hutool.core.io.FileUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.global.Constant;

import java.io.File;

/**
 * 资源服务器
 *
 * @author Esion
 * @since 2021/8/3
 */
@RestController
@RequestMapping("resource")
public class ResourceController {

    @GetMapping("image/{fileName}")
    public ResponseEntity<byte[]> image(@PathVariable("fileName") String fileName){
        File file = FileUtil.file(Constant.IMAGE_PATH, fileName);
        if (!file.exists()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(FileUtil.readBytes(file));
    }

    @GetMapping("music/{fileName}")
    public ResponseEntity<byte[]> music(@PathVariable("fileName") String fileName){
        File file = FileUtil.file(Constant.MUSIC_PATH, fileName);
        if (!file.exists()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(FileUtil.readBytes(file));
    }

}
