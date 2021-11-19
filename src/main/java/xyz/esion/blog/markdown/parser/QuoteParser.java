package xyz.esion.blog.markdown.parser;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.esion.blog.markdown.Template;

import java.io.Serializable;
import java.util.List;

/**
 * @author Esion
 * @since 2021/11/19
 */
public class QuoteParser {

    private static final int LENGTH = 2;

    public static Quote render(String value) {
        List<String> title = StrUtil.splitTrim(value, ' ', 2);
        if (title.size() == LENGTH) {
            return new Quote(title.get(1));
        } else {
            return new Quote(value);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Quote implements Serializable {

        private static final long serialVersionUID = 1L;

        private String value;

        public String html() {
            return StrUtil.format(Template.QUOTE, value);
        }

    }

}
