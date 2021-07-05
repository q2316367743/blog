package xyz.esion.blog.entity.dto;

import java.io.Serializable;

/**
 * 新增评论
 *
 * @author Esion
 * @since 2021/7/4
 */
public class CommentReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickname;
    private String email;
    private String website;
    private String content;
    private String system;
    private String browser;
    private Integer articleId;
    private Integer commentId;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "CommentSaveDTO{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", content='" + content + '\'' +
                ", system='" + system + '\'' +
                ", browser='" + browser + '\'' +
                ", articleId=" + articleId +
                ", commentId='" + commentId + '\'' +
                '}';
    }
}
