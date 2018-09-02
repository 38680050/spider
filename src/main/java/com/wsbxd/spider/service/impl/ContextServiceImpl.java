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
import org.springframework.util.StringUtils;

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

    /**
     * 根据链接获取小说内容
     * @param url 链接
     * @return 小说内容
     */
    @Override
    public Content getContext(String url) {
        try {
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
            content.setTitle( doc.select(contentTitleSelectorSelect).get( getIndex( contentTitleSelectorIndex ) ).text() );

            //获取章节内容
            NovelSiteXmlPares.Selector contentContentSelector = parserRule.getContentContentSelector();
            String contentContentSelectorSelect = contentContentSelector.getSelect();
            String contentContentSelectorIndex = contentContentSelector.getIndex();
            content.setContent( doc.select( contentContentSelectorSelect ).get( getIndex( contentContentSelectorIndex ) ).text() );

            //获取上一章
            NovelSiteXmlPares.Selector parserRuleContentPrevSelector = parserRule.getContentPrevSelector();
            String contentPrevSelectorSelect = parserRuleContentPrevSelector.getSelect();
            String contentPrevSelectorIndex = parserRuleContentPrevSelector.getIndex();
            content.setPrev( doc.select( contentPrevSelectorSelect ).get( getIndex( contentPrevSelectorIndex ) ).text() );

            //获取下一章
            NovelSiteXmlPares.Selector contentNextSelector = parserRule.getContentNextSelector();
            String nextSelectorSelect = contentNextSelector.getSelect();
            String nextSelectorIndex = contentNextSelector.getIndex();
            content.setNext( doc.select( nextSelectorSelect ).get( getIndex( nextSelectorIndex ) ).text() );

            return content;
        } catch (NumberFormatException e) {
            throw new RuntimeException( e + " | 获取小说内容错误！" );
        }
    }

    /**
     * 获取下标，如果下表为空，则默认返回0
     * @param selectorIndex 选择器下标
     * @return 下标数
     */
    private int getIndex(String selectorIndex) {
        int index = 0;
        if (!StringUtils.isEmpty( selectorIndex )){
            index = Integer.parseInt( selectorIndex );
        }
        return index;
    }
}
