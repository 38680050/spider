package com.wsbxd.spider.mapper;

import com.wsbxd.common.utils.TkMapper;
import com.wsbxd.spider.domain.po.NovelSite;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/15 15:50
 */
@Mapper
@Repository
public interface NovelSiteMapper extends TkMapper<NovelSite> {
}
