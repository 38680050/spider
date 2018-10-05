package com.wsbxd.spider.domain.po;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * description: 用户实体类
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2018/10/5 10:44
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t_user")
public class User {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 邮箱
     */
    @Column
    private String email;

    /**
     * 用户名
     */
    @Column
    private String username;

    /**
     * 模式
     */
    @Column
    private String schema;

}
