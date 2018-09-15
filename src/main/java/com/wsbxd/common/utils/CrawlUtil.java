package com.wsbxd.common.utils;

import com.wsbxd.common.constant.Constant;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description: 抓取工具类
 *
 * @author chenhaoxuan
 * @date 2018/9/2 10:19
 */
@Component
public class CrawlUtil {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 抓取页面元素
     * protected访问权限范围为：类内、同包、不同包子类
     * try(...){}的写法可以自动帮你释放()里的资源
     * @param url 链接
     * @return 页面内容
     */
    public String crawl(String url){
        try (
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url))
        ) {
            //根据Response和页面编码格式获取页面result
            String hashKey = null;
            if (redisUtil.hashKey(Constant.REDIS_NOVEL_SITE_CHARSET,NovelSiteEnum.getByUrl(url).getUrl())){
                hashKey = (String) redisUtil.getHashKey(Constant.REDIS_NOVEL_SITE_CHARSET, NovelSiteEnum.getByUrl(url).getUrl());
            }
            return EntityUtils.toString(httpResponse.getEntity(), hashKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据下标获取选择器属性
     * @param url                链接
     * @param redisSelectorEnum  Redis选择器
     * @param index              下标
     * @return 选择器
     */
    public String getSelectorByIndex(String url,RedisSelectorEnum redisSelectorEnum,Integer index){
        //获取Redis中的选择器
        String[] split = ((String)redisUtil.getHashKey(redisSelectorEnum.getRedisName(), NovelSiteEnum.getByUrl(url).getUrl())).split("\\|");
        if (split.length == 1){
            if (index.equals(0)){
                return split[index];
            }else if(index.equals(1)){
                return "0";
            }else{
                throw new RuntimeException("您的选择器下标不在规定的选择器下标范围内!");
            }
        }else if(split.length == Constant.NUMBER_TWO){
            return split[index];
        }else{
            throw new RuntimeException("您的选择器下标不在规定的选择器下标范围内!");
        }
    }

}
