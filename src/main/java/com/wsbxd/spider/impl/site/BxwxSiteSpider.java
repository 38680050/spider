package com.wsbxd.spider.impl.site;

import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.common.utils.RedisSelectorEnum;
import com.wsbxd.spider.domain.po.Type;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 看书中小说站点爬取
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2018/10/6 11:33
 */
public class BxwxSiteSpider extends AbstractSiteSpider {

    @Override
    public List<Type> crawlTypes(String url) {
        String result = crawl( url );
        Document doc = Jsoup.parse( result );
        doc.setBaseUri( url );
        List<Type> types = new ArrayList<>();
        Elements elements = doc.select(getSelectorByIndex(url, RedisSelectorEnum.TYPE, 0));
        for (Element element:elements) {
            types.add(new Type(null,element.text().replaceAll(" ",""),element.absUrl("href"),url));
        }
        return types;
    }

}
