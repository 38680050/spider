package com.wsbxd.common.utils;

import com.wsbxd.common.domain.NovelSiteXmlPares;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: 小说站点工具类
 *
 * @author chenhaoxuan
 * @date 2018/9/2 11:22
 */
public final class NovelSiteUtil {

    /**
     * 初始化常量时要把常量放在前面，否则初始化失败
     */
    private final static Map<NovelSiteEnum,NovelSiteXmlPares> CONTEXT_MAP = new HashMap<>();

    static {
        init();
    }

    /**
     * 初始化方法
     */
    private static void init(){
        SAXReader reader = new SAXReader();
        try {
            //获取小说站点信息
            Document doc = reader.read(NovelSiteUtil.class.getResourceAsStream("/conf/Novel-Site-Rule.xml"));
            //获取根节点
            Element rootElement = doc.getRootElement();
            //获取站点
            List<Element> sites = rootElement.elements("site");
            for (Element site : sites) {
                //章节标题选择器
                String title = site.element( "title" ).getTextTrim();
                //字符编码选择器
                String charset = site.element( "charset" ).getTextTrim();
                //链接选择器
                String url = site.element( "url" ).getTextTrim();
                //章节列表选择器
                Element element = site.element( "chapter-list-selector" );
                String chapterListSelector = element.getTextTrim();
                String chapterListSelectorIndex = element.attribute( "index" ).getValue();
                //章节标题选择器
                Element element1 = site.element( "content-title-selector" );
                String contentTitleSelector = element1.getTextTrim();
                String contentTitleSelectorIndex = element1.attribute( "index" ).getValue();
                //章节内容选择器
                Element element2 = site.element( "content-content-selector" );
                String contentContentSelector = element2.getTextTrim();
                String contentContentSelectorIndex = element2.attribute( "index" ).getValue();
                //上一章选择器
                Element element3 = site.element( "content-prev-selector" );
                String contentPrevSelector = element3.getTextTrim();
                String contentPrevSelectorIndex = element3.attribute( "index" ).getValue();
                //下一章选择器
                Element element4 = site.element( "content-next-selector" );
                String contentNextSelector = element4.getTextTrim();
                String contentNextSelectorIndex = element4.attribute( "index" ).getValue();
                NovelSiteXmlPares novelSiteXmlPares = new NovelSiteXmlPares(
                        title,
                        charset,
                        url,
                        new NovelSiteXmlPares.Selector( chapterListSelector, chapterListSelectorIndex ),
                        new NovelSiteXmlPares.Selector( contentTitleSelector, contentTitleSelectorIndex ),
                        new NovelSiteXmlPares.Selector( contentContentSelector, contentContentSelectorIndex ),
                        new NovelSiteXmlPares.Selector( contentPrevSelector, contentPrevSelectorIndex ),
                        new NovelSiteXmlPares.Selector( contentNextSelector, contentNextSelectorIndex )
                );
                NovelSiteEnum novelSiteEnum = NovelSiteEnum.getByUrl(url);
                CONTEXT_MAP.put(novelSiteEnum, novelSiteXmlPares);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对应网站的解析规则  Parser（解析）
     * @param novelSiteEnum
     * @return
     */
    public static NovelSiteXmlPares getParserRule(NovelSiteEnum novelSiteEnum){
        return CONTEXT_MAP.get(novelSiteEnum);
    }

    /**
     * 私有化无参构造
     */
    private NovelSiteUtil(){}

}
