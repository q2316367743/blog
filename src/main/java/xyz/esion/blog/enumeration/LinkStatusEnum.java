package xyz.esion.blog.enumeration;

/**
 * @author Esion
 * @since 2021/11/27
 */
public enum LinkStatusEnum {

    /**
     * 审批中
     */
    APPLY(1),
    /**
     * 审批通过
     */
    PASS(2),
    /**
     * 审批拒绝
     */
    REFUSE(3);

    private final Integer value;

    LinkStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
