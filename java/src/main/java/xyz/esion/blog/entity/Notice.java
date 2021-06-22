package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 通知
 * @TableName t_notice
 * @author Esion
 * @since 2021/6/22
 */
@TableName(value ="t_notice")
public class Notice implements Serializable {
    /**
     * 文章ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容，html字符串
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Boolean isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 文章ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 通知标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 通知标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 通知内容，html字符串
     */
    public String getContent() {
        return content;
    }

    /**
     * 通知内容，html字符串
     */
    public void setContent(String content) {
        this.content = content;
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
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 逻辑删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 逻辑删除
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}