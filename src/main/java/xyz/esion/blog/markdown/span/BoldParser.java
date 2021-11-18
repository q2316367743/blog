package xyz.esion.blog.markdown.span;

import xyz.esion.blog.markdown.AbstractMarkdownSpanParser;

/**
 * 加粗解析器
 *
 * @author Esion
 * @since 2021/11/18
 */
public class BoldParser extends AbstractMarkdownSpanParser {

    public BoldParser(String originalValue) {
        super(originalValue);
    }

    @Override
    public String parse() {
        return "<b>" + getOriginalValue() + "</b>";
    }

}
