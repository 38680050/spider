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
            result = result.replace("&nbsp;", "  ").replace( "m.11kt.cn","" ).replace( "顶点小说手机站","" );
            Document doc = Jsoup.parse( result );
            doc.setBaseUri( url );

            //根据链接获取小说站点枚举，再根据小说站点枚举获取小说站点的解析规则
            NovelSiteXmlPares parserRule = NovelSiteUtil.getParserRule( NovelSiteEnum.getByUrl( url ) );
            Content content = new Content();

            //获取标题内容
            content.setTitle( doc.select(parserRule.getContentTitle(0)).get( Integer.parseInt(parserRule.getContentTitle(1)) ).text() );

            //获取章节内容
            content.setContent( doc.select( parserRule.getContentContent(0) ).get( Integer.parseInt(parserRule.getContentContent(1) ) ).text() );

            //获取上一章
            content.setPrev( doc.select( parserRule.getContentPrev(0) ).get( Integer.parseInt(parserRule.getContentPrev(1)) ).text() );

            //获取下一章
            content.setNext( doc.select( parserRule.getContentNext(0) ).get( Integer.parseInt(parserRule.getContentNext(1)) ).text() );

            return content;
        } catch (NumberFormatException e) {
            throw new RuntimeException( e + " | 获取小说内容错误！" );
        }
    }
}
