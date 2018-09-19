package com.wsbxd.spider.interfaces;

import com.wsbxd.spider.domain.po.Chapter;

import java.util.List;

/**
 * description: 章节爬虫接口
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 16:03
 */
public interface IChapterSpider {

    /**
     * 根据链接获取章节列表
     * @param url 链接
     * @return 章节列表
     */
    List<Chapter> getChapters(String url);

}
