package xyz.esion.blog;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.esion.blog.service.FileService;

@SpringBootTest(classes = BlogApplication.class)
@RunWith(SpringRunner.class)
class JavaApplicationTests {

    @Autowired
    private FileService fileService;

    @Test
    void contextLoads() {
        System.out.println(fileService.link("C:\\Users\\Esion\\.blog\\static\\flashScreen.png"));
    }

}
