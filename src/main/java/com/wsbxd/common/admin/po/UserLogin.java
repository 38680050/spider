package com.wsbxd.common.admin.po;

import lombok.*;

import javax.persistence.Column;

/**
 * description: 用户登录实体类
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2018/10/5 17:41
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLogin {

    /**
     * 邮箱
     */
    @Column
    private String email;

    /**
     * 模式
     */
    @Column
    private String schema;

}
