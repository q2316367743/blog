package xyz.esion.blog.markdown;

import java.util.List;

/**
 * @author Esion
 * @since 2021/11/18
 */
public interface MarkdownParser {

    /**
     * 将markdown转化为html
     * @return html代码
     */
    String html();

}
