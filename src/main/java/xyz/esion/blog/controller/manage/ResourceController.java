package xyz.esion.blog.controller.manage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.ConfigService;
import xyz.esion.blog.service.ResourceService;

import java.io.IOException;

/**
 * @author Esion
 * @since 2021/11/29
 */
@RestController("resource_manage")
@RequestMapping("manage/api/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    private final ConfigService configService;

    @PostMapping
    public Result<String> upload(MultipartFile file, String path) throws IOException {
        return Result.success(configService.info().getHref() + "/resource" + path + "/" + resourceService.update(path, file));
    }

}
