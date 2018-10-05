package com.wsbxd.spider.service;

import com.wsbxd.spider.domain.po.Site;

import java.util.List;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/15 15:48
 */
public interface ISiteService {

    /**
     * 查询所有小说站点
     * @return 所有小说站点
     */
    List<Site> findAll();

}
