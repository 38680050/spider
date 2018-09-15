package com.wsbxd.spider.service.impl;

import com.wsbxd.common.utils.RedisSelectorEnum;
import com.wsbxd.spider.domain.po.NovelSite;
import com.wsbxd.common.utils.CrawlUtil;
import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.mapper.ChapterMapper;
import com.wsbxd.spider.service.IChapterService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/1 15:45
 */
@Service
public class ChapterServiceImpl implements IChapterService {

    @Autowired
    private CrawlUtil crawlUtil;

    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 根据链接获取小说章节
     * @param url 链接
     * @return
     */
    @Override
    public List<Chapter> getChapter(String url) {
        try {
            String result = crawlUtil.crawl(url);
            Document doc = Jsoup.parse(result);
            //根据章节列表选择器获取对应的标签
            Elements elements = doc.select(crawlUtil.getSelectorByIndex(url, RedisSelectorEnum.LIST,0));
            List<Chapter> chapters = new ArrayList<>();
            for (Element a:elements) {
                chapters.add(new Chapter(null,a.text(),a.attr("href")));
            }
            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
