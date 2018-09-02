package com.wsbxd.common.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * description: 继承自己的MyMapper
 *
 * @author chenhaoxuan
 * @date 2018/8/23 18:19
 * @version 1.0
 */
public interface TkMapper<T> extends Mapper<T>, MySqlMapper<T> {
}