package com.wsbxd.spider.impl.content;

import com.wsbxd.common.utils.RedisSelectorEnum;
import com.wsbxd.spider.domain.po.Content;
import com.wsbxd.spider.impl.AbstractSpider;
import com.wsbxd.spider.interfaces.IContentSpider;
import com.wsbxd.spider.mapper.ContentMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description: 小说内容爬虫抽象类
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 16:38
 */
public abstract class AbstractContentSpider extends AbstractSpider implements IContentSpider {

    @Autowired
    protected ContentMapper contentMapper;

}
