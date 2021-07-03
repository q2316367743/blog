package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName v_notice_list
 * @author Esion
 * @since 2021/6/22
 */
@TableName(value ="v_notice_list")
public class NoticeList implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容，html字符串
     */
    private String content;

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
}