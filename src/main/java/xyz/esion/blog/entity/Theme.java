package xyz.esion.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 主题表
 * @TableName theme
 * @author Esion
 * @since 2021/12/24
 */
@TableName(value ="theme")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theme implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 同步时间
     */
    private Date syncTime;

    /**
     * 启用时间
     */
    private Date enableTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 是否启用
     */
    private Integer isEnable;

    /**
     * 主题类型
     */
    private Integer type;

    /**
     * 来源
     */
    private String source;

    /**
     * 名称
     */
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}