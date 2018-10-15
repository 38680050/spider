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

    /**
     * 根据链接清空并添加小说类型
     * @param siteUrl   链接
     * @return 是否成功
     */
    boolean insertTypes(String siteUrl);

}
