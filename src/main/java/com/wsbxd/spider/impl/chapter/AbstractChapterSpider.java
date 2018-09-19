package com.wsbxd.spider.impl.chapter;

import com.wsbxd.common.utils.RedisSelectorEnum;
import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.impl.AbstractSpider;
import com.wsbxd.spider.interfaces.IChapterSpider;
import com.wsbxd.spider.mapper.ChapterMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 章节爬虫抽象类
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 16:30
 */
public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {

    @Autowired
    protected ChapterMapper chapterMapper;

    public List<Chapter> findAll(){
        return chapterMapper.selectAll();
    }

}
