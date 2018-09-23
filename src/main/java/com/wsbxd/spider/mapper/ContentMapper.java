package com.wsbxd.spider.mapper;

import com.wsbxd.common.utils.TkMapper;
import com.wsbxd.spider.domain.po.Content;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/1 15:46
 */
@Mapper
@Repository("contentMapper")
public interface ContentMapper extends TkMapper<Content> {



}
