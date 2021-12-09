package xyz.esion.blog.controller.manage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.service.FileService;

/**
 * @author Esion
 * @since 2021/12/9
 */
@RestController
@RequestMapping("manage/api/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

}
