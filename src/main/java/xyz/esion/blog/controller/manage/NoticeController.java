package xyz.esion.blog.controller.manage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.esion.blog.service.NoticeService;

/**
 * @author Esion
 * @since 2021/11/27
 */
@RestController
@RequestMapping("/manage/api/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

}
