package com.wsbxd.spider.service.impl;

import com.wsbxd.common.domain.NovelSiteXmlPares;
import com.wsbxd.common.utils.CrawlUtil;
import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.common.utils.NovelSiteUtil;
import com.wsbxd.spider.domain.po.Content;
import com.wsbxd.spider.mapper.ContextMapper;
import com.wsbxd.spider.service.IContextService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/2 17:07
 */
@Service
public class ContextServiceImpl implements IContextService {

    @Autowired
    private ContextMapper contextMapper;

    @Override
    public Content getContext(String url) {
        String result = CrawlUtil.crawl( url );
        result = result.replace("&nbsp;", "  ").replace("<br />", "\n").replace("<br/>", "\n");
        Document doc = Jsoup.parse( result );
        doc.setBaseUri( url );

        //根据链接获取小说站点枚举，再根据小说站点枚举获取小说站点的解析规则
        NovelSiteXmlPares parserRule = NovelSiteUtil.getParserRule( NovelSiteEnum.getByUrl( url ) );
        Content content = new Content();

        //获取标题内容
        NovelSiteXmlPares.Selector contentTitleSelector = parserRule.getContentTitleSelector();
        String contentTitleSelectorSelect = contentTitleSelector.getSelect();
        String contentTitleSelectorIndex = contentTitleSelector.getIndex();
        content.setTitle( doc.select(contentTitleSelectorSelect).get(Integer.parseInt(contentTitleSelectorIndex)).text() );


        return null;
    }
}
