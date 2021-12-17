package xyz.esion.blog.controller.manage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.esion.blog.constant.PathConstant;
import xyz.esion.blog.domain.Config;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.param.FileParam;
import xyz.esion.blog.service.ConfigService;
import xyz.esion.blog.service.FileService;
import xyz.esion.blog.service.ResourceService;
import xyz.esion.blog.view.file.FileListView;
import xyz.esion.blog.view.file.FileResourceView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Esion
 * @since 2021/11/29
 */
@RestController("resource_manage")
@RequestMapping("manage/api/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final FileService fileService;
    private final ResourceService resourceService;
    private final ConfigService configService;

    @PostMapping("upload")
    public Result<String> upload(MultipartFile file, String path) throws IOException {
        return Result.success(configService.info().getHref() + "/resource" + path + "/" + resourceService.update(buildPath(path), file));
    }

    @GetMapping("ls")
    public Result<List<FileResourceView>> ls(@RequestParam(value = "path", required = false, defaultValue = "") String path) {
        boolean flag = !PathConstant.WEB_SEPARATOR.equals(File.separator);
        List<FileListView> views = fileService.ls(buildPath(path));
        Config config = configService.info();
        return Result.success(views.stream().map(item -> {
            FileResourceView view = new FileResourceView();
            view.setName(item.getName());
            view.setIsDirectory(item.getIsDirectory());
            String absPath = item.getPath().substring(PathConstant.RESOURCE_PATH.length());
            if (flag) {
                absPath = absPath.replace(File.separator, "/");
            }
            view.setPath(absPath);
            if (!item.getIsDirectory()) {
                view.setLink(config.getHref() + "/resource" + absPath);
            }else {
                view.setLink("");
            }
            return view;
        }).collect(Collectors.toList()));
    }

    @GetMapping("link")
    public Result<String> link(@RequestParam(value = "path", required = false, defaultValue = "") String path) {
        return Result.success(fileService.link(buildPath(path)));
    }

    @PostMapping("mkdir")
    public Result<Boolean> mkdir(@RequestBody FileParam param) {
        param.setPath(buildPath(param.getPath()));
        fileService.mkdir(param);
        return Result.success();
    }

    @DeleteMapping("rm")
    public Result<Boolean> rm(@RequestParam(value = "path", required = false, defaultValue = "") String path) {
        if ("".equals(path)) {
            throw new IllegalArgumentException("无法删除根目录");
        }
        fileService.remove(buildPath(path));
        return Result.success();
    }

    @GetMapping("command")
    public Result<List<String>> command(
            @RequestParam(value = "path", required = false, defaultValue = "") String path,
            @RequestParam(value = "command", required = false, defaultValue = "") String command
    ) throws IOException {
        return Result.success(fileService.execCommandByPath(buildPath(path), command));
    }

    private String buildPath(String path) {
        if (!PathConstant.WEB_SEPARATOR.equals(File.separator)) {
            path = path.replace(PathConstant.WEB_SEPARATOR, File.separator);
        }
        return PathConstant.RESOURCE_PATH + path;
    }

}
