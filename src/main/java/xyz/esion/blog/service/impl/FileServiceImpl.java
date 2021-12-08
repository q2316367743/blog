package xyz.esion.blog.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.esion.blog.constant.PathConstant;
import xyz.esion.blog.domain.Config;
import xyz.esion.blog.service.ConfigService;
import xyz.esion.blog.service.FileService;
import xyz.esion.blog.view.file.FileListView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Esion
 * @since 2021/12/8
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ConfigService configService;

    @Override
    public List<FileListView> ls(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("目录不存在");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("路径不是目录");
        }
        File[] files = file.listFiles();
        if (files == null) {
            return new LinkedList<>();
        }
        List<FileListView> views = new LinkedList<>();
        for (File item : files) {
            FileListView view = new FileListView();
            view.setName(item.getName());
            view.setPath(item.getAbsolutePath());
            view.setIsDirectory(item.isDirectory());
            views.add(view);
        }
        return views;
    }

    @Override
    public String link(String path) {
        Config config = configService.info();
        if (path.startsWith(PathConstant.RESOURCE_PATH)) {
            // 资源路径下，问项目路径+resource+路径
            return config.getHref() + "/resource" + path.substring(PathConstant.RESOURCE_PATH.length()).replace("\\", "/");
        } else {
            return "";
        }
    }

    @Override
    public void download(String path, HttpServletResponse response) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("当前路径不存在");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("当前路径不是文件");
        }
        response.setContentType(FileTypeUtil.getType(file));
        response.setCharacterEncoding("utf-8");
        response.setContentLengthLong(file.length());
        String fileName = URLEncoder.encode(file.getName(), "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName);
        IoUtil.write(response.getOutputStream(), true, FileUtil.readBytes(file));
    }

    @Override
    public void upload(String path, MultipartFile multipartFile) throws IOException {
        String name = multipartFile.getOriginalFilename();
        name = URLUtil.decode(name);
        if (StrUtil.isBlank(name)) {
            // 如果没有内容则为时间戳
            name = System.currentTimeMillis() + "";
        }
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("目录不存在");
        }
        FileUtil.writeBytes(IoUtil.readBytes(multipartFile.getInputStream()), new File(file, name));
    }

    @Override
    public void remove(String path) {

    }

    @Override
    public List<String> execCommandByPath(String path, String command) throws IOException {
        return RuntimeUtil.getResultLines(new ProcessBuilder(command).redirectErrorStream(true).directory(new File(path)).start());
    }

}
