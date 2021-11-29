package xyz.esion.blog.controller.portal;

import cn.hutool.core.util.URLUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.service.ResourceService;

import javax.servlet.http.HttpServletRequest;

/**
 * 资源控制器
 *
 * @author Esion
 * @since 2021/11/29
 */
@RestController("resource")
@RequestMapping("resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("**/*")
    public ResponseEntity<byte[]> download(HttpServletRequest request) {
        String uri = request.getRequestURI();
        uri = uri.substring(10);
        uri = URLUtil.decode(uri);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(resourceService.download(uri));
    }

}
