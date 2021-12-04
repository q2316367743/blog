package xyz.esion.blog.enumeration;

/**
 * 评论来源类型
 *
 * @author Esion
 * @since 2021/12/4
 */
public enum CommentSourceTypeEnum {

    /**
     * 文章
     */
    ARTICLE(1),
    /**
     * 页面
     */
    PAGE(2);

    private final Integer value;

    CommentSourceTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static CommentSourceTypeEnum getByValue(Integer value) {
        for (CommentSourceTypeEnum commentSourceTypeEnum : values()) {
            if (commentSourceTypeEnum.getValue().equals(value)) {
                return commentSourceTypeEnum;
            }
        }
        return null;
    }
}
