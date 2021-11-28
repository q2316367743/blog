package xyz.esion.blog.enumeration;

/**
 * @author Esion
 * @since 2021/11/28
 */
public enum CommentStatusEnum {

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

    CommentStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
