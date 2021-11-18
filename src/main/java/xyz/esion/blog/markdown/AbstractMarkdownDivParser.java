package xyz.esion.blog.markdown;


import xyz.esion.blog.markdown.span.BoldParser;

/**
 * 块级元素解析
 *
 * @author Esion
 * @since 2021/11/18
 */
public abstract class AbstractMarkdownDivParser extends AbstractMarkdownParser {

    public AbstractMarkdownDivParser(String originalValue) {
        super(originalValue);
    }

    /**
     * 渲染块级元素
     *
     * @param value 处理内嵌元素后的内容
     * @return html代码
     */
    public abstract String render(String value);

    @Override
    public String parse() {
        return render(parseSpan());
    }

    public String parseSpan() {
        StringBuilder sb = new StringBuilder();
        // 对内嵌元素解析
        char[] items = this.getOriginalValue().toCharArray();
        int index = 0;
        while (index < items.length) {
            char item = items[index];
            if (item == '*') {
                if (items[index + 1] == '*') {
                    // 加粗
                    StringBuilder temp = new StringBuilder();
                    for (int i = index + 2;;i++) {
                        if (items[i] == '*') {
                            if (items[i + 1] == '*') {
                                index = i + 1;
                                break;
                            }
                        }
                        temp.append(items[i]);
                    }
                    sb.append(new BoldParser(temp.toString()).html());
                }
            }else {
                sb.append(item);
            }
            index += 1;
        }
        return sb.toString();
    }

}
