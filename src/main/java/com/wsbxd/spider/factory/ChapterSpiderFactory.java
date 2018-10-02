package com.wsbxd.spider.factory;

import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.spider.impl.chapter.DefaultChapterSpider;
import com.wsbxd.spider.interfaces.IChapterSpider;

/**
 * description: 章节列表爬虫工厂
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 17:37
 */
public final class ChapterSpiderFactory {

    /**
     * 根据链接获取
     * @param url
     * @return
     */
    public static IChapterSpider getChapterSpider(String url){
        IChapterSpider spider;
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getByUrl(url);
        switch (novelSiteEnum){
            case BXWX: spider = new DefaultChapterSpider();break;
            case KSZ: spider = new DefaultChapterSpider();break;
            default: spider = new DefaultChapterSpider();break;
        }
        return spider;
    }

    private ChapterSpiderFactory() {
    }
}
