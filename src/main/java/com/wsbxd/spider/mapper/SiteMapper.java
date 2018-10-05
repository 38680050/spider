package com.wsbxd.spider.mapper;

import com.wsbxd.common.utils.TkMapper;
import com.wsbxd.spider.domain.po.Site;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/15 15:50
 */
@Mapper
@Repository("novelSiteMapper")
public interface SiteMapper extends TkMapper<Site> {

    /**
     * 根据URL获取站点ID
     * @param url 链接
     * @return 站点ID
     */
    Integer findSiteIdByUrl(String url);

}
