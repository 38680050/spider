package com.wsbxd.common.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * description: 抓取工具类
 *
 * @author chenhaoxuan
 * @date 2018/9/2 10:19
 */
public class CrawlUtil {

    /**
     * 抓取页面元素
     * protected访问权限范围为：类内、同包、不同包子类
     * try(...){}的写法可以自动帮你释放()里的资源
     * @param url 链接
     * @return
     */
    public static String crawl(String url){
        try (
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url))
        ) {
            //根据Response和页面编码格式获取页面result
            String result = EntityUtils.toString(httpResponse.getEntity(),NovelSiteUtil.getParserRule(NovelSiteEnum.getByUrl(url)).getCharset());
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
