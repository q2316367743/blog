package xyz.esion.blog.module.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Controller("portalRouter")
@RequestMapping
public class RouterController {

    @GetMapping
    public String index() {
        return "index";
    }

}
