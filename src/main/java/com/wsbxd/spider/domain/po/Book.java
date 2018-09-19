package com.wsbxd.spider.domain.po;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * description: 图书标题
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
    @Column
    private String title;

    /**
     * 链接
     */
    @Column
    private String url;

    /**
     * 作者
     */
    @Column
    private String author;

    /**
     * 小说的类别：如武侠修真，都市言情
     */
    @Column
    private String type;

    /**
     * 小说最后更新的时间
     */
    @Column
    private String lastUpdateTime;

    /**
     * 小说的状态：1 连载 2 完结
     */
    @Column
    private Integer status;

    /**
     * 入库时间
     */
    @Column
    private String addTime;

    /**
     * 站点ID
     */
    @Column
    private Integer siteId;

}
