package xyz.esion.blog.markdown;

import xyz.esion.blog.markdown.div.TitleParser;

/**
 * markdown文档解析器
 *
 * @author Esion
 * @since 2021/11/18
 */
public class Markdown {

    private final String html;

    public Markdown(String original) {
        this.html = parse(original);
    }

    private String parse(String original) {
        StringBuilder sb = new StringBuilder();
        String[] lines = original.split("\n");
        for (String line : lines) {
            // 解析
            if (line.startsWith("#")) {
                sb.append(new TitleParser(line).html());
            }
        }
        return sb.toString();
    }

    public String html() {
        return html;
    }

    public void menu() {}

}
