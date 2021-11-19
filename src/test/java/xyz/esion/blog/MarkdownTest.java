package xyz.esion.blog;

import xyz.esion.blog.markdown.Markdown;

/**
 * @author Esion
 * @since 2021/11/18
 */
public class MarkdownTest {

    public static void main(String[] args) {
        Markdown markdown = new Markdown("## 你~~真~~,`好`\n" +
                "\n" +
                "> aaa\n" +
                ">\n" +
                "你~~真~~好");
        System.out.println(markdown.html());
    }

}
