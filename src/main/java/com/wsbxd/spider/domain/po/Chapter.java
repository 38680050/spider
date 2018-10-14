package com.wsbxd.spider.domain.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * description: 小说实体章节
 *
 * @author chenhaoxuan
 * @date 2018/9/1 15:49
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t_chapter")
public class Chapter implements Serializable {

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
     * 链接
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * bookID
     */
    @Column(name = "bookUrl")
    private String bookUrl;

}
