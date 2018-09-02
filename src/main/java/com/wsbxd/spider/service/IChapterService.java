package com.wsbxd.spider.service;

import com.wsbxd.spider.domain.po.Chapter;

import java.util.List;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/1 15:43
 */
public interface IChapterService {

    /**
     * 根据链接获取章节列表
     * @param url 链接
     * @return 章节列表
     */
    List<Chapter> getChapter(String url);

}
