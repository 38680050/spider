package com.wsbxd.spider.impl.site;

import com.wsbxd.common.utils.RedisSelectorEnum;
import com.wsbxd.spider.constant.SiteEnum;
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

    private final static Integer TWO = 2;

    @Override
    public List<Type> crawlTypes(String url) {
        String result = crawl( url );
        Document doc = Jsoup.parse( result );
        doc.setBaseUri( url );
        List<Type> types = new ArrayList<>();
        Elements elements = doc.select(getSelectorByIndex(url, RedisSelectorEnum.TYPE, 0));
        for (int i = 1; i < elements.size() - TWO; i++) {
            Element element = elements.get(i);
            types.add(new Type(null,element.text().replaceAll(" ",""),element.absUrl("href"), SiteEnum.getByUrl(url).getId()));
        }
        return types;
    }

}
