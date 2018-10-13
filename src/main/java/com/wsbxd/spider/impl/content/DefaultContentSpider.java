package com.wsbxd.spider.impl.content;

import com.wsbxd.common.utils.RedisSelectorEnum;
import com.wsbxd.spider.domain.po.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * description: 默认小说内容爬取实体类
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 16:46
 */
public class DefaultContentSpider extends AbstractContentSpider {

    /**
     * 根据链接获取小说内容
     * @param url 链接
     * @return 小说内容
     */
    @Override
    public Content crawlContent(String url) {
        try {
            String result = crawl( url );
            result = result.replace("&nbsp;", "  ").replace( "m.11kt.cn","" ).replace( "顶点小说手机站","" );
            Document doc = Jsoup.parse( result );
            doc.setBaseUri( url );

            //根据链接获取小说站点枚举，再根据小说站点枚举获取小说站点的解析规则
            Content content = new Content();

            //获取标题内容
            content.setTitle( doc.select(getSelectorByIndex(url, RedisSelectorEnum.TITLE,0))
                    .get( Integer.parseInt(getSelectorByIndex(url, RedisSelectorEnum.TITLE,1)) ).text() );

            //获取章节内容
            content.setContent( doc.select( getSelectorByIndex(url, RedisSelectorEnum.CONTENT,0) )
                    .get( Integer.parseInt( getSelectorByIndex(url, RedisSelectorEnum.CONTENT,1) ) ).text() );

            //获取上一章
            content.setPrev( doc.select( getSelectorByIndex(url, RedisSelectorEnum.PREV,0) )
                    .get( Integer.parseInt( getSelectorByIndex(url, RedisSelectorEnum.PREV,1) ) ).absUrl("href") );

            //获取下一章
            content.setNext( doc.select( getSelectorByIndex(url, RedisSelectorEnum.NEXT,0) )
                    .get( Integer.parseInt( getSelectorByIndex(url, RedisSelectorEnum.NEXT,1) ) ).absUrl("href") );

            return content;
        } catch (NumberFormatException e) {
            throw new RuntimeException( e + " | 获取小说内容错误！" );
        }
    }

}
