package xyz.esion.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 系统配置
 *
 * @author qiao shengda
 * @since 2021/6/20
 */
@Data
@Component
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页面链接
     */
    private String href;

    /**
     * 博客名称
     */
    private String name;

    /**
     * 博客关键字
     */
    private List<String> keywords;

    /**
     * 图标
     */
    private String favicon;

    /**
     * 首页背景图片
     */
    private String background;

    /**
     * 页面默认背景图片
     */
    private String pageBackground;

    /**
     * 文章默认背景图片
     */
    private String articleBackground;

    /**
     * 分类默认背景图片
     */
    private String categoryBackground;

    /**
     * 归档默认背景图片
     */
    private String archiveBackground;

    /**
     * 友链默认背景图片
     */
    private String linkBackground;

    /**
     * 关于默认背景图片
     */
    private String aboutBackground;

    /**
     * 备案
     */
    private String keepOnRecord;

    private Index index;

    public List<Music> music;

    @Data
    public static class Music implements Serializable {

        private static final long serialVersionUID = 1L;

        private String url;

        private String cover;

        private String name;

        private String artist;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Index implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 首页轮播文字
         */
        private List<String> texts;

        private Integer speed;

        private Boolean loop;

    }

}
