package com.wsbxd.spider.interfaces;

import com.wsbxd.spider.domain.po.Type;

import java.util.List;

/**
 * description: 小说站点爬取接口
 *
 * @author chenhaoxuan
 * @date 2018/10/5 22:11
 */
public interface ISiteSpider {

    List<Type> crawlTypes(String url);

}
