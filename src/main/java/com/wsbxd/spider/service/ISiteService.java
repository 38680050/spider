package com.wsbxd.spider.service;

import com.wsbxd.spider.domain.po.Site;
import com.wsbxd.spider.domain.po.Type;

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
    List<Site> selectSiteAll();

    /**
     * 根据站点URL查询类型集合
     * @param siteUrl   站点URL
     * @return  类型集合
     */
    List<Type> selectTypesBySiteUrl(String siteUrl);

    /**
     * 根据siteUrl删除小说类型
     * @param siteUrl   站点链接
     */
    void deleteTypeBySiteUrl(String siteUrl);

    /**
     * 根据siteUrl添加小说类型
     * @param siteUrl   站点链接
     * @return 是否成功
     */
    boolean insertTypes(String siteUrl);

}
