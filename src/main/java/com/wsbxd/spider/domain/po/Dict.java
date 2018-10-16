package com.wsbxd.spider.domain.po;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * description: 字典实体类
 *
 * @author chenhaoxuan
 * @date 2018/10/13 19:41
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "t_dict")
public class Dict {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 名称
     */
    @Column(name = "mapName")
    private String mapName;

    /**
     * 类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 父id
     */
    @Column(name = "pid")
    private Integer pid;

}
