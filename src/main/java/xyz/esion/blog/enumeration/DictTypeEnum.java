package xyz.esion.blog.enumeration;

/**
 * @author Esion
 * @since 2021/12/5
 */
public enum DictTypeEnum {

    /**
     * 意见反馈类型
     */
    MESSAGE_TYPE(1),
    /**
     * 通知类型
     */
    NOTICE_TYPE(2);

    private final Integer value;

    DictTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
