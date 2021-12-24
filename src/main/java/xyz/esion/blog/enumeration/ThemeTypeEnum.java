package xyz.esion.blog.enumeration;

/**
 * @author Esion
 * @since 2021/12/24
 */
public enum ThemeTypeEnum {

    /**
     * 未知
     */
    DEFAULT(0),
    /**
     * 远程
     */
    REMOTE(1),
    /**
     * 本地
     */
    LOCAL(2);

    private final Integer value;

    ThemeTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
