package xyz.esion.blog.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.esion.blog.constant.PathConstant;
import xyz.esion.blog.service.ResourceService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author Esion
 * @since 2021/11/29
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Override
    public void download(String path, HttpServletResponse response) throws IOException {
        File file = new File(PathConstant.RESOURCE_PATH + File.separator + path);
        if (!file.exists()) {
            return;
        }
        response.setContentType(FileTypeUtil.getType(file));
        response.setContentLengthLong(file.length());
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(file.getName(), "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName);
        IoUtil.write(response.getOutputStream(), true, FileUtil.readBytes(file));
    }

    @Override
    public String update(String path, MultipartFile multipartFile) throws IOException {
        String name = multipartFile.getOriginalFilename();
        name = URLUtil.decode(name);
        if (StrUtil.isBlank(name)) {
            // 如果没有内容则为时间戳
            name = System.currentTimeMillis() + "";
        }
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("路径不是文件夹");
        }
        if (!file.exists()) {
            if (!file.mkdirs()) {
                file = new File(PathConstant.RESOURCE_PATH);
            }
        }
        FileUtil.writeBytes(IoUtil.readBytes(multipartFile.getInputStream()), new File(file, name));
        return name;
    }

}
