package com.wsbxd.spider.factory;

import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.spider.impl.content.DefaultContentSpider;
import com.wsbxd.spider.interfaces.IContentSpider;

/**
 * description: 小说内容工厂
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 17:03
 */
public final class ContentSpiderFactory {

    /**
     * 根据链接获取IContentSpider具体实现类
     * @param url 链接
     * @return  IContentSpider具体实现类
     */
    public static IContentSpider getContentSpider(String url){
        IContentSpider spider;
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getByUrl(url);
        switch (novelSiteEnum){
            case BiQuGe:            spider = new DefaultContentSpider();break;
            case DingDianXiaoShuo:  spider = new DefaultContentSpider();break;
            default:                spider = new DefaultContentSpider();break;
        }
        return spider;
    }

}
