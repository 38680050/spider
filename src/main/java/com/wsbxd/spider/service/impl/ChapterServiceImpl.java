package com.wsbxd.spider.service.impl;

import com.wsbxd.spider.mapper.ChapterMapper;
import com.wsbxd.spider.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: 章节列表impl
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2018/10/10 14:49
 */
@Service
public class ChapterServiceImpl implements IChapterService {

    private final ChapterMapper chapterMapper;

    @Autowired
    public ChapterServiceImpl(ChapterMapper chapterMapper) {
        this.chapterMapper = chapterMapper;
    }
}
