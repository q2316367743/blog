package xyz.esion.blog.enumerate;

/**
 * 文章排序字段
 *
 * @author Esion
 * @since 2021/7/4
 */
public enum ArticleOrderEnum {

    // 创建时间
    CREATE_TIME(1, "create_time"),
    // 更新时间
    UPDATE_TIME(2, "update_time"),
    // 浏览人数
    VIEW_COUNT(3, "view_count"),
    // 文章字数
    WORD_COUNT(4, "word_count"),
    // 评论数
    COMMENT_COUNT(5, "comment_count");

    public static String getFieldById(Integer id){
        for (ArticleOrderEnum item : values()) {
            if (item.getId().equals(id)){
                return item.getField();
            }
        }
        return null;
    }

    /**
     * ID
     * */
    private Integer id;

    /**
     * 字段值
     * */
    private String field;

    ArticleOrderEnum(Integer id, String field) {
        this.id = id;
        this.field = field;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
