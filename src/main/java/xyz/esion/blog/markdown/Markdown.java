package xyz.esion.blog.markdown;

import xyz.esion.blog.global.KeyValue;
import xyz.esion.blog.markdown.parser.SpanParser;
import xyz.esion.blog.markdown.parser.TitleParser;

import java.util.LinkedList;
import java.util.List;

/**
 * markdown文档解析器
 *
 * @author Esion
 * @since 2021/11/18
 */
public class Markdown {

    private final String html;
    private final List<KeyValue<Integer, String>> menus;

    public Markdown(String original) {
        this.menus = new LinkedList<>();
        this.html = parse(original);
    }

    private String parse(String original) {
        StringBuilder sb = new StringBuilder();
        String[] lines = original.split("\n");
        for (String line : lines) {
            // 解析
            if (line.startsWith("#")) {
                TitleParser.Title title = TitleParser.render(line);
                title.setValue(SpanParser.render(title.getValue()));
                sb.append(title.html());
                menus.add(new KeyValue<>(title.getLevel(), title.getValue()));
            }
        }
        return sb.toString();
    }

    public String html() {
        return html;
    }

    public void menu() {}

}
