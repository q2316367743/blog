package xyz.esion.blog.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.esion.blog.view.file.FileListView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 文件操作
 *
 * @author Esion
 * @since 2021/12/8
 */
public interface FileService {

    /**
     * 列出目录下全部文件
     * @param path 目录
     * @return 全部目录/文件
     */
    List<FileListView> ls(String path);

    /**
     * 获取外部链接
     *
     * @param path 文件
     * @return 链接，如果无法获取，则为空字符串
     */
    String link(String path);

    /**
     * 文件下载
     *
     * @param path 文件路径
     * @param response 响应
     * @throws IOException IO异常
     */
    void download(String path, HttpServletResponse response) throws IOException;

    /**
     * 文件上传
     *
     * @param path 文件夹路径
     * @param multipartFile 上传的文件
     * @throws IOException IO异常
     */
    void upload(String path, MultipartFile multipartFile) throws IOException;

    /**
     * 文件/文件夹删除
     *
     * @param path 文件/文件夹
     */
    void remove(String path);

    /**
     * 在指定目录下执行命令
     *
     * @param path 目录
     * @param command 命令
     * @return 结果
     */
    List<String> execCommandByPath(String path, String command) throws IOException;

}
