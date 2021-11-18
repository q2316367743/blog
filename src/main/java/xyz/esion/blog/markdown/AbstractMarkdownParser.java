package xyz.esion.blog.markdown;

import lombok.Data;

/**
 * @author Esion
 * @since 2021/11/18
 */
@Data
public abstract class AbstractMarkdownParser implements MarkdownParser {

    private final String originalValue;

    public AbstractMarkdownParser(String originalValue) {
        this.originalValue = originalValue.trim();
    }

    @Override
    public String html() {
        return parse();
    }

    /**
     * 真正地解析器
     *
     * @return HTML代码
     */
    public abstract String parse();

}
