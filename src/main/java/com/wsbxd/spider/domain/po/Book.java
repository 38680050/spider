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
     * 作者
     */
    @Column
    private String author;

    /**
     * 链接
     */
    @Column
    private String url;

}
