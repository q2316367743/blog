package xyz.esion.blog.markdown.div;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import xyz.esion.blog.markdown.AbstractMarkdownDivParser;

import java.util.List;

/**
 * 标题解析器，最多支持6级标题
 *
 *
 * @author Esion
 * @since 2021/11/18
 */
@Slf4j
public class TitleParser extends AbstractMarkdownDivParser {

    private static final Integer MAX_LEVEL = 6;

    public TitleParser(String originalValue) {
        super(originalValue);
    }

    @Override
    public String render(String value) {
        List<String> title = StrUtil.splitTrim(value, ' ', 2);
        int level = title.get(0).length();
        if (level > MAX_LEVEL) {
            throw new IllegalArgumentException("标题最多支持6级：" + this.getOriginalValue());
        }
        return StrUtil.format("<h{}>{}</h{}>", level, title.get(1), level);
    }

}
