package xyz.esion.blog.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Rss每一项
 *
 * @author Esion
 * @since 2021/12/10
 */
@Data
public class RssItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String description;

    private String author;

    private String pubDate;

    private String link;

    private String guid;

}
