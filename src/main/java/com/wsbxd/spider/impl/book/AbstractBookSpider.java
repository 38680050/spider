package com.wsbxd.spider.impl.book;

import com.wsbxd.common.constant.Constant;
import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.spider.impl.AbstractSpider;
import com.wsbxd.spider.interfaces.IBookSpider;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * description: 小说图书爬虫抽象类
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/20 14:39
 */
public abstract class AbstractBookSpider extends AbstractSpider implements IBookSpider {

    /**
     * 下一页的元素
     */
    protected Element nextPageElement;

    /**
     * 下一页的url
     */
    protected String nextPage;

    /**
     * 默认抓取方法,最多尝试 {@link Constant#MAX_TRY_NUM} 次下载调用
     * @param url   链接
     * @return  元素
     */
    protected Elements getTrs(String url){
        return getTrs(url, Constant.MAX_TRY_NUM);
    }

    protected Elements getTrs(String url,Integer maxTryNum){
        maxTryNum = maxTryNum.equals(null) ? Constant.MAX_TRY_NUM : maxTryNum;
        Elements elements = null;
        for (int i = 0 ; i < maxTryNum ; i++ ){
            String result = super.crawl(url);
            redisUtil.getHashKey(Constant.REDIS_NOVEL_SITE_CHARSET, NovelSiteEnum.getByUrl(url).getUrl());
        }
        return null;
    }

}
