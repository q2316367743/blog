package xyz.esion.blog.markdown.span;

import xyz.esion.blog.markdown.AbstractMarkdownSpanParser;

/**
 * 斜体解析器
 *
 * @author Esion
 * @since 2021/11/18
 */
public class ItalicsParser extends AbstractMarkdownSpanParser {

    public ItalicsParser(String originalValue) {
        super(originalValue);
    }

    @Override
    public String parse() {
        return "<i>" + this.getOriginalValue() + "</i>";
    }

}
