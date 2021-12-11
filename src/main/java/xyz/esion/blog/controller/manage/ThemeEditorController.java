package xyz.esion.blog.controller.manage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.esion.blog.constant.FolderConstant;
import xyz.esion.blog.constant.PathConstant;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.param.FileUpdateParam;
import xyz.esion.blog.service.FileService;
import xyz.esion.blog.view.file.FileTreeView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 主题编辑器
 *
 * @author Esion
 * @since 2021/12/11
 */
@RestController
@RequestMapping("manage/api/theme/editor")
@RequiredArgsConstructor
public class ThemeEditorController {

    private final FileService fileService;

    /**
     * 获取全部的文件列表
     */
    @GetMapping("ls")
    public Result<List<FileTreeView>> files() {
        List<FileTreeView> views = new LinkedList<>();
        views.add(fileService.tree(PathConstant.TEMPLATES_PATH, PathConstant.HOME_PATH));
        views.add(fileService.tree(PathConstant.STATIC_PATH, PathConstant.HOME_PATH));
        return Result.success(views);
    }

    @GetMapping("cat")
    public Result<String> cat(String path, String charset) {
        return Result.success(fileService.cat(buildPath(path), charset));
    }

    @GetMapping("download")
    public void download(String path, HttpServletResponse response) throws IOException {
        fileService.download(buildPath(path), response);
    }

    @PostMapping("write")
    public Result<Boolean> write(@RequestBody FileUpdateParam param) {
        param.setPath(buildPath(param.getPath()));
        fileService.write(param.getPath(), param.getContent());
        return Result.success();
    }

    private String buildPath(String path) {
        // 只能访问特定目录
        if (!path.startsWith(PathConstant.WEB_SEPARATOR + FolderConstant.TEMPLATES_FOLDER) &&
                !path.startsWith(PathConstant.WEB_SEPARATOR + FolderConstant.STATIC_FOLDER)) {
            throw new IllegalArgumentException("访问路径出错");
        }
        // 修改分隔符
        if (!PathConstant.WEB_SEPARATOR.equals(File.separator)) {
            path = path.replace(PathConstant.WEB_SEPARATOR, File.separator);
        }
        return PathConstant.HOME_PATH + path;
    }

}
