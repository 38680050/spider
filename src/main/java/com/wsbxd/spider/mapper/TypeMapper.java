package com.wsbxd.spider.mapper;

import com.wsbxd.common.utils.TkMapper;
import com.wsbxd.spider.domain.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * description: 小说类型mapper
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2018/10/6 11:57
 */
@Mapper
@Repository("typeMapper")
public interface TypeMapper extends TkMapper<Type> {



}
