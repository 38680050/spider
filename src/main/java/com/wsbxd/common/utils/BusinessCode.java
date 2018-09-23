package com.wsbxd.common.utils;

import lombok.*;

/**
 * @author: HP
 * @date: 2018年8月1日
 * @description: 枚举常量类
 */
@Getter
@AllArgsConstructor
@ToString
public enum BusinessCode {

    /**
     * 返回数据
     */
    GLOBAL_SUCCESS("200", "成功"),
    GLOBAL_ERROR("500", "系统正在维护中,请稍后再试!");

    private String code;

    private String msg;

}
