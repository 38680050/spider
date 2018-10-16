package com.wsbxd.spider.service;

import com.wsbxd.spider.domain.po.NovelSelector;

import java.util.List;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/15 16:55
 */
public interface INovelSelectorService {

    /**
     * 查询所有小说选择器
     * @return 所有小说选择器
     */
    List<NovelSelector> selectAll();

}
