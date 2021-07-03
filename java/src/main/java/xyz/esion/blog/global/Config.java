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
        this.background = "https://esion.xyz/assets/image/background.jpg";
        List<Music> music = new LinkedList<>();
//        music.add(new Music("http://esion.xyz/music/刘瑞琦 - 房间 (新版).flac", "https://esion.xyz/assets/image/default.jpg", "房间", "刘瑞琪"));
//        music.add(new Music("http://esion.xyz/music/陈粒 - 走马.flac", "https://esion.xyz/assets/image/default.jpg", "走马", "陈粒"));
//        music.add(new Music("http://esion.xyz/music/水木年华 - 一生有你.flac", "https://esion.xyz/assets/image/default.jpg", "一生有你", "水木年华"));
//        music.add(new Music("http://esion.xyz/music/程jiajia - 山楂树の恋.flac", "https://esion.xyz/assets/image/default.jpg", "山楂树之恋", "程佳佳"));
//        music.add(new Music("http://esion.xyz/music/蒋雪儿 - 燕无歇.flac", "https://esion.xyz/assets/image/default.jpg", "燕无歌", "蒋雪儿"));
//        music.add(new Music("http://esion.xyz/music/火箭少女101段奥娟 - 陪我长大.flac", "https://esion.xyz/assets/image/default.jpg", "陪我长大", "段奥娟"));
//        music.add(new Music("http://esion.xyz/music/陈一发儿 - 童话镇.flac", "https://esion.xyz/assets/image/default.jpg", "童话镇", "陈一发"));
//        music.add(new Music("http://esion.xyz/music/汪苏泷、By2 - 有点甜.flac", "https://esion.xyz/assets/image/default.jpg", "有点甜", "汪苏泷、By2"));
//        music.add(new Music("http://esion.xyz/music/王力宏 - 需要人陪.flac", "https://esion.xyz/assets/image/default.jpg", "需要人陪", "王力宏"));
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
