package com.wsbxd.spider.impl.chapter;

import com.wsbxd.common.utils.RedisSelectorEnum;
import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.impl.AbstractSpider;
import com.wsbxd.spider.interfaces.IChapterSpider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

    /**
     * 根据链接获取小说章节
     * @param url 链接
     * @return 章节列表list
     */
    @Override
    public List<Chapter> getChapters(String url) {
        try {
            String result = crawl(url);
            Document doc = Jsoup.parse(result);
            //根据章节列表选择器获取对应的标签
            Elements elements = doc.select(getSelectorByIndex(url, RedisSelectorEnum.LIST,0));
            List<Chapter> chapters = new ArrayList<>();
            for (Element a:elements) {
                chapters.add(new Chapter(null,a.text(),a.attr("href"),null,null));
            }
            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e + "章节列表爬取失败!");
        }
    }
}
