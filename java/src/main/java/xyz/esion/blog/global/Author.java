package xyz.esion.blog.global;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>作者信息</p>
 * <p>全局</p>
 *
 * @author qiao shengda
 * @since 2021/6/20
 */
@Component
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

    public Author() {
        this.name = "云落天都";
        this.description = "主要是Java技术栈，略带部分源码，随笔";
        this.avatar = "https://portrait.gitee.com/uploads/avatars/user/1786/5358547_qiaoshengda_1578985319.png!avatar200";
        this.gitee = "https://gitee.com/qiaoshengda";
        List<Other> other = new LinkedList<>();
        other.add(new Other("CSDN", "https://g.csdnimg.cn/static/logo/favicon32.ico", "https://blog.csdn.net/q2316367743"));
        this.other = other;
        this.baseInfo = "<h1>基本信息</h1>\n" +
                "\t\t\t\t\t<p>\uD83D\uDC68基本情况：大四在读，计算机科学与技术专业。</p>\n" +
                "\t\t\t\t\t<p>\n" +
                "\t\t\t\t\t\t\uD83C\uDFAB昵称：云落天都。云落天都，月出九皋。\n" +
                "\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t<p>\uD83C\uDF0D地址：目前在湖北孝感。</p>\n" +
                "\t\t\t\t\t<p>\n" +
                "\t\t\t\t\t\t\uD83D\uDCE7邮箱：<a href=\"mailto:m17762618644@163.com\"\n" +
                "\t\t\t\t\t\t\t>m17762618644@163.com</a\n" +
                "\t\t\t\t\t\t>\n" +
                "\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t<p>\uD83D\uDD28技术方向：主Java，会考虑学一点前端和大数据。</p>\n" +
                "\t\t\t\t\t<p>\uD83D\uDC93兴趣爱好：游戏，编程。</p>\n" +
                "\t\t\t\t\t<p>\uD83D\uDCDD写作风格：一般以学习笔记为主，少量的心得感悟。</p>";
    }

    class Other implements Serializable {

        private static final long serialVersionUID = 1L;

        private String name;
        private String icon;
        private String href;

        public Other() {
        }

        public Other(String name, String icon, String href) {
            this.name = name;
            this.icon = icon;
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGitee() {
        return gitee;
    }

    public void setGitee(String gitee) {
        this.gitee = gitee;
    }

    public List<Other> getOther() {
        return other;
    }

    public void setOther(List<Other> other) {
        this.other = other;
    }

    public String getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(String baseInfo) {
        this.baseInfo = baseInfo;
    }
}
