package com.wsbxd.spider.mapper;

import com.wsbxd.spider.domain.po.NovelSelector;
import com.wsbxd.common.utils.TkMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/15 16:57
 */
@Mapper
@Repository
public interface NovelMapper extends TkMapper<NovelSelector> {
}
