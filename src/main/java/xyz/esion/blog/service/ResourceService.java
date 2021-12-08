package xyz.esion.blog.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 资源服务
 *
 * @author Esion
 * @since 2021/11/29
 */
public interface ResourceService {

    /**
     * 在指定路径下获取资源
     *
     * @param path 资源路径
     * @throws IOException IO异常
     */
    void download(String path, HttpServletResponse response) throws IOException;

    /**
     * 上传文件到指定目录下
     *
     * @param path 目录
     * @param multipartFile 文件
     * @return  文件路径
     * @throws IOException IO异常
     */
    String update(String path, MultipartFile multipartFile) throws IOException;

}
