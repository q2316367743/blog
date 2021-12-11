package xyz.esion.blog.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.esion.blog.param.FileParam;
import xyz.esion.blog.view.file.FileListView;
import xyz.esion.blog.view.file.FileTreeView;

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
     * 以树的形式列出指定文件夹下全部文件
     *
     * @param path 文件夹路径
     * @param prefix 文件路径前缀
     * @return 全部文件
     */
    FileTreeView tree(String path, String prefix);

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
     * 查看文件内容<br />
     * 默认使用UTF-8进行编码
     * @param path 文件路径
     * @param charset 编码，默认为UTF-8
     * @return 内容
     */
    String cat(String path, String charset);

    /**
     * 想目标文件写入内容<br />
     * 默认使用UTF-8进行编码
     *
     * @param path 文件路径
     * @param content 写入的内容
     */
    void write(String path, String content);

    /**
     * 文件上传
     *
     * @param path 文件夹路径
     * @param multipartFile 上传的文件
     * @throws IOException IO异常
     */
    void upload(String path, MultipartFile multipartFile) throws IOException;

    /**
     * 新建文件夹
     *
     * @param param 参数
     */
    void mkdir(FileParam param);

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
     * @throws IOException IO异常
     */
    List<String> execCommandByPath(String path, String command) throws IOException;

}
