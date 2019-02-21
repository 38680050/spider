package com.wsbxd.spider.domain.po;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * description: 小说类型实体类
 *
 * @author chenhaoxuan
 * @date 2018/10/5 22:25
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "t_type")
public class Type {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 类型名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 站点url
     */
    @Column(name = "siteId")
    private Integer siteId;

}
