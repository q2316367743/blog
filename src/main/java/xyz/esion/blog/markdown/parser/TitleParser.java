package xyz.esion.blog.markdown.parser;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.esion.blog.markdown.Template;

import java.io.Serializable;
import java.util.List;

/**
 * 标题解析器，最多支持6级标题
 *
 *
 * @author Esion
 * @since 2021/11/18
 */
@Slf4j
public class TitleParser {

    private static final Integer MAX_LEVEL = 6;

    public static Title render(String value) {
        List<String> title = StrUtil.splitTrim(value, ' ', 2);
        int level = title.get(0).length();
        if (level > MAX_LEVEL) {
            throw new IllegalArgumentException("标题最多支持6级：" + value);
        }
        return new Title(level, title.get(1));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Title implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer level;

        private String value;

        public String html() {
            return StrUtil.format(Template.TITLE, level, value, level);
        }

    }

}
