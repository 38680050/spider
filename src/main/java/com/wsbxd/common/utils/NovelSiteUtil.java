package com.wsbxd.common.utils;

import com.wsbxd.common.domain.NovelSiteXmlPares;
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
    private final static Map<NovelSiteEnum,NovelSiteXmlPares> NOVEL_SITE_MAP = new HashMap<>();

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
                String chapterListSelector = site.element("chapter-list-selector").getTextTrim();
                //章节标题选择器
                String contentTitleSelector = site.element("content-title-selector").getTextTrim();
                //章节内容选择器
                String contentContentSelector = site.element("content-content-selector").getTextTrim();
                //上一章选择器
                String contentPrevSelector = site.element("content-prev-selector").getTextTrim();
                //下一章选择器
                String contentNextSelector = site.element("content-next-selector").getTextTrim();
                NovelSiteXmlPares novelSiteXmlPares = new NovelSiteXmlPares(
                        title,
                        charset,
                        url,
                        chapterListSelector,
                        contentTitleSelector,
                        contentContentSelector,
                        contentPrevSelector,
                        contentNextSelector
                );
                NovelSiteEnum novelSiteEnum = NovelSiteEnum.getByUrl(url);
                NOVEL_SITE_MAP.put(novelSiteEnum, novelSiteXmlPares);
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
        return NOVEL_SITE_MAP.get(novelSiteEnum);
    }

    /**
     * 私有化无参构造
     */
    private NovelSiteUtil(){}

}
