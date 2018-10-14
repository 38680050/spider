package com.wsbxd.spider.impl.chapter;

import com.wsbxd.common.utils.RedisSelectorEnum;
import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.mapper.ChapterMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 默认小说章节列表爬虫实体类
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 16:44
 */
public class DefaultChapterSpider extends AbstractChapterSpider {

    /**
     * 根据链接获取小说章节
     * @param url 链接
     * @return 章节列表list
     */
    @Override
    public List<Chapter> crawlChapters(String url) {
        try {
            String result = crawl(url);
            Document doc = Jsoup.parse(result);
            doc.setBaseUri(url);
            //根据章节列表选择器获取对应的标签
            Elements elements = doc.select(getSelectorByIndex(url, RedisSelectorEnum.LIST,0));
            List<Chapter> chapters = new ArrayList<>();
            Integer sort = 1;

            for (Element a:elements) {
                chapters.add(new Chapter(null,a.text(),a.absUrl("href"),sort++,url));
            }
            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e + " | 章节列表爬取失败!");
        }
    }

}
