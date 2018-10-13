package com.wsbxd.spider.interfaces;

import com.wsbxd.spider.domain.po.Content;

/**
 * description: 小说内容爬取接口
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 16:04
 */
public interface IContentSpider {

    /**
     * 根据链接获取章节内容
     * @param url 链接
     * @return  章节内容
     */
    Content crawlContent(String url);

}
