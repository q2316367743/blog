package xyz.esion.blog.enumeration;

/**
 * @author Esion
 * @since 2021/11/22
 */
public enum MessageTypeEnum {

    /**
     * 博客
     */
    BLOG(1),
    /**
     * es-client
     */
    ES_CLIENT(2);

    private final Integer value;

    MessageTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
