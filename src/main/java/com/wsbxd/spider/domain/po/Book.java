package com.wsbxd.spider.domain.po;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * description: 书籍标题
 *
 * @author chenhaoxuan
 * @date 2018/9/15 15:37
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t_book")
public class Book {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 作者
     */
    @Column(name = "author")
    private String author;

    /**
     * 小说的类别：如武侠修真，都市言情
     */
    @Column(name = "type")
    private String type;

    /**
     * 小说最后更新的时间
     */
    @Column(name = "lastUpdateTime")
    private String lastUpdateTime;

    /**
     * 小说最后更新的章节名
     */
    @Column(name = "lastUpdateContent")
    private String lastUpdateContent;

    /**
     * 小说最后更新的章节链接
     */
    @Column(name = "lastUpdateContentUrl")
    private String lastUpdateContentUrl;

    /**
     * 小说的状态：连载 完结
     */
    @Column(name = "status")
    private String status;

    /**
     * 入库时间
     */
    @Column(name = "addTime")
    private String addTime;

    /**
     * 修改时间
     */
    @Column(name = "updateTime")
    private String updateTime;

    /**
     * 站点ID
     */
    @Column(name = "siteId")
    private Integer siteId;

}
