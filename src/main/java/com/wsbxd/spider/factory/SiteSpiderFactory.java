package com.wsbxd.spider.factory;

import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.spider.impl.site.KanShuZhongSiteSpider;
import com.wsbxd.spider.interfaces.ISiteSpider;

/**
 * description: 站点爬虫抽象工厂
 *
 * @author chenhaoxuan
 * @date 2018/10/7 21:41
 */
public final class SiteSpiderFactory {

    public ISiteSpider getSiteService(String url){
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getByUrl(url);
        switch (novelSiteEnum) {
            case BXWX: return new KanShuZhongSiteSpider();
            case KSZ: return new KanShuZhongSiteSpider();
            default : throw new RuntimeException(url + "暂时不被支持");
        }
    }

    private SiteSpiderFactory() {
    }
}
