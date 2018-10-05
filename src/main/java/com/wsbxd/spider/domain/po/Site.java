package com.wsbxd.spider.domain.po;

import lombok.*;

import javax.persistence.*;

/**
 * description: 小说站点实体类
 *
 * @author chenhaoxuan
 * @date 2018/9/2 17:57
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "t_site")
public class Site {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 站点名
     */
    @Column
    private String title;
    /**
     * 字符编码
     */
    @Column
    private String charset;
    /**
     * 链接
     */
    @Column
    private String url;

}
