package xyz.esion.blog.enumeration;

/**
 * @author Esion
 * @since 2021/11/28
 */
public enum CommentTypeEnum {

    /**
     * 访客
     */
    VISITOR(1),
    /**
     * 朋友
     */
    FRIEND(2),
    /**
     * 作者自己
     */
    AUTHOR(3);

    private final Integer value;

    CommentTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
