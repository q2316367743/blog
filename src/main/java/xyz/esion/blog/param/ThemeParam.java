package xyz.esion.blog.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/12/24
 */
@Data
public class ThemeParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主题类型{@link xyz.esion.blog.enumeration.ThemeTypeEnum}
     * <br />
     * 注意：<br />
     * 如果是远程，则项目存放在<strong>/主题文件夹/名称</strong><br />
     * 如果是本地，则在本地目录
     */
    private Integer type;

    /**
     * 来源，远程地址/本地地址
     */
    private String source;

    /**
     * 名称
     */
    private String name;

}
