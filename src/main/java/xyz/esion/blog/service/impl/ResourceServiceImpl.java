package xyz.esion.blog.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.esion.blog.constant.PathConstant;
import xyz.esion.blog.service.ResourceService;

import java.io.File;
import java.io.IOException;

/**
 * @author Esion
 * @since 2021/11/29
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Override
    public byte[] download(String path) {
        File file = new File(PathConstant.RESOURCE_PATH + File.separator + path);
        if (!file.exists()) {
            return new byte[0];
        }
        return FileUtil.readBytes(file);
    }

    @Override
    public String update(String path, MultipartFile multipartFile) throws IOException {
        String name = multipartFile.getOriginalFilename();
        name = URLUtil.decode(name);
        if (StrUtil.isBlank(name)) {
            // 如果没有内容则为时间戳
            name = System.currentTimeMillis() + "";
        }
        FileUtil.writeBytes(IoUtil.readBytes(multipartFile.getInputStream()), PathConstant.RESOURCE_PATH + File.separator + path + File.separator + name);
        return name;
    }

}
