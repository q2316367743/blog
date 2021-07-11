package xyz.esion.blog.global;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 系统配置
 *
 * @author qiao shengda
 * @since 2021/6/20
 */
@Component
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    private String background;

    public List<Music> music;

    public Config() {
        this.background = "background.jpg";
        List<Music> music = new LinkedList<>();
        this.music = music;
    }

    class Music implements Serializable {

        private static final long serialVersionUID = 1L;

        public Music() {
        }

        public Music(String url, String cover, String name, String artist) {
            this.url = url;
            this.cover = cover;
            this.name = name;
            this.artist = artist;
        }

        private String url;

        private String cover;

        private String name;

        private String artist;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public List<Music> getMusic() {
        return music;
    }

    public void setMusic(List<Music> music) {
        this.music = music;
    }
}
