package com.wsbxd.spider.mapper;

import com.wsbxd.common.utils.TkMapper;
import com.wsbxd.spider.domain.po.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * description: 字典mapper
 *
 * @author chenhaoxuan
 * @date 2018/10/13 19:45
 */
@Mapper
@Repository("dictMapper")
public interface DictMapper extends TkMapper<Dict> {



}
