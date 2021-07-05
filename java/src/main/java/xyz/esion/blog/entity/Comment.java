package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 评论表
 * @TableName t_comment
 */
@TableName(value ="t_comment")
public class Comment implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
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
     * 是否展示，0：未审查，1：通过，2：不通过
     */
    private Integer isShow;

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
     * 所在评论的ID
     */
    private Integer pId;

    /**
     * 回复对象昵称，冗余
     */
    private String targetNickname;

    /**
     * 回复对象个人网站，冗余
     */
    private String targetWebsite;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 个人网站
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 个人网站
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 是否展示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 是否展示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * 浏览器版本
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * 浏览器版本
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * 系统
     */
    public String getSystemVersion() {
        return systemVersion;
    }

    /**
     * 系统
     */
    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    /**
     * 评论类型；1：访客
     */
    public Integer getType() {
        return type;
    }

    /**
     * 评论类型；1：访客
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 文章ID
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 文章ID
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 所在评论的ID
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * 所在评论的ID
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }

    /**
     * 回复对象昵称，冗余
     */
    public String getTargetNickname() {
        return targetNickname;
    }

    /**
     * 回复对象昵称，冗余
     */
    public void setTargetNickname(String targetNickname) {
        this.targetNickname = targetNickname;
    }

    /**
     * 回复对象个人网站，冗余
     */
    public String getTargetWebsite() {
        return targetWebsite;
    }

    /**
     * 回复对象个人网站，冗余
     */
    public void setTargetWebsite(String targetWebsite) {
        this.targetWebsite = targetWebsite;
    }
}