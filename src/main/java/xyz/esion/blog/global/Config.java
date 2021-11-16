package xyz.esion.blog.global;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 系统配置
 *
 * @author qiao shengda
 * @since 2021/6/20
 */
@Data
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    private String background;

    public List<Music> music;

    @Data
    public static class Music implements Serializable {

        private static final long serialVersionUID = 1L;

        private String url;

        private String cover;

        private String name;

        private String artist;

    }

}
