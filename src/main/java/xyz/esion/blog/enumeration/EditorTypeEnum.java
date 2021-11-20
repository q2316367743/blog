package xyz.esion.blog.enumeration;

/**
 * 编辑器类型
 *
 * @author Esion
 * @since 2021/11/20
 */
public enum EditorTypeEnum {

    // markdown
    MARKDOWN(1),
    // 富文本
    RICH_TEXT(2),
    // html代码
    HTML(3);

    private final Integer value;

    EditorTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
