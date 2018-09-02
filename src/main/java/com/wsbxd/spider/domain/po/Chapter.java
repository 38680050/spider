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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
