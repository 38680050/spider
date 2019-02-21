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
     * 根据站点ID查询类型集合
     * @param siteId   站点URL
     * @return  类型集合
     */
    List<Type> selectTypesBySiteId(Integer siteId);

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

    /**
     * 根据链接获取小说类型
     * @param url   链接
     * @return  小说类型
     */
    Type selectTypeByUrl(String url);
}
