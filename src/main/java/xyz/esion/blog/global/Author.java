package xyz.esion.blog.global;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>作者信息</p>
 * <p>全局</p>
 *
 * @author qiao shengda
 * @since 2021/6/20
 */
@Data
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String avatar;
    private String gitee;
    private List<Other> other;

    /**
     * 基本信息
     *
     * */
    private String baseInfo;

    @Data
    public static class Other implements Serializable {

        private static final long serialVersionUID = 1L;

        private String name;
        private String icon;
        private String href;

    }

}
