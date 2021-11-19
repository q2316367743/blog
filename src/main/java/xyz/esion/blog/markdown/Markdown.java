package xyz.esion.blog.markdown;

import cn.hutool.core.util.StrUtil;
import xyz.esion.blog.global.KeyValue;
import xyz.esion.blog.markdown.parser.QuoteParser;
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

    private static final String SEPARATE = "\n";

    private static final String TITLE = "#";
    private static final String QUOTE = ">";
    private static final String IMAGE = "!";

    public Markdown(String original) {
        this.menus = new LinkedList<>();
        this.html = parse(original);
    }

    private String parse(String original) {
        StringBuilder sb = new StringBuilder();
        String[] lines = original.split(SEPARATE);
        for (String line : lines) {
            // 解析
            if (line.startsWith(TITLE)) {
                TitleParser.Title title = TitleParser.render(line);
                title.setValue(SpanParser.render(title.getValue()));
                sb.append(title.html()).append(SEPARATE);
                menus.add(new KeyValue<>(title.getLevel(), title.getValue()));
            }else if (line.startsWith(QUOTE)) {
                QuoteParser.Quote quote = QuoteParser.render(line);
                quote.setValue(SpanParser.render(quote.getValue()));
                sb.append(quote.html()).append(SEPARATE);
            }else {
                // 普通段落
                sb.append(StrUtil.format(Template.PARAGRAPH, SpanParser.render(line))).append(SEPARATE);
            }
        }
        return sb.toString();
    }

    public String html() {
        return html;
    }

    public List<KeyValue<Integer, String>> menu() {
        return menus;
    }

}
