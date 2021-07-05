package xyz.esion.blog.entity.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论展示
 *
 * @author qiao shengda
 * @since 2021/7/5
 */
public class CommentListFront implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 个人网站
     */
    private String website;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 浏览器版本
     */
    private String browser;

    /**
     * 系统
     */
    private String systemVersion;

    /**
     * 评论类型；1：访客
     */
    private Integer type;

    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 回复对象昵称，冗余
     */
    private String targetNickname;

    /**
     * 回复对象个人网站，冗余
     */
    private String targetWebsite;

    /**
     * 评论内容
     */
    private String content;

    private List<CommentListFront> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTargetNickname() {
        return targetNickname;
    }

    public void setTargetNickname(String targetNickname) {
        this.targetNickname = targetNickname;
    }

    public String getTargetWebsite() {
        return targetWebsite;
    }

    public void setTargetWebsite(String targetWebsite) {
        this.targetWebsite = targetWebsite;
    }

    public List<CommentListFront> getChildren() {
        return children;
    }

    public void setChildren(List<CommentListFront> children) {
        this.children = children;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentListFront{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createTime=" + createTime +
                ", browser='" + browser + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                ", type=" + type +
                ", articleId=" + articleId +
                ", targetNickname='" + targetNickname + '\'' +
                ", targetWebsite='" + targetWebsite + '\'' +
                ", content='" + content + '\'' +
                ", children=" + children +
                '}';
    }
}
