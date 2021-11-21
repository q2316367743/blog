package xyz.esion.blog.enumeration;

/**
 * @author Esion
 * @since 2021/11/21
 */
public enum ArticleStatusEnum {

    /**
     * 草稿
     */
    DRAFT(0),
    /**
     * 已发布
     */
    RELEASE(1),
    /**
     * 回收站
     */
    DELETE(2);

    private final Integer value;

    ArticleStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
