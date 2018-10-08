package com.wsbxd.spider.factory;

import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.spider.impl.book.BxwxBookSpider;
import com.wsbxd.spider.impl.book.KszBookSpider;
import com.wsbxd.spider.interfaces.IBookSpider;

/**
 * description: 书籍爬虫工厂类
 *
 * @author chenhaoxuan
 * @date 2018/10/2 21:26
 */
public final class BookSpiderFactory {

    public static IBookSpider getBookSpider(String url){
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getByUrl(url);
        switch (novelSiteEnum) {
            case BXWX: return new BxwxBookSpider();
            case KSZ: return new KszBookSpider();
            default : throw new RuntimeException(url + "暂时不被支持");
        }
    }

    private BookSpiderFactory() {
    }

}
