package com.wsbxd.spider.impl;

import com.wsbxd.common.constant.Constant;
import com.wsbxd.common.utils.BeanHelper;
import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.common.utils.RedisSelectorEnum;
import com.wsbxd.common.utils.RedisUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * description: 抽象爬虫类
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 15:56
 */
public abstract class AbstractSpider  {

    private static RedisUtil redisUtil;

    static{
        //抽象类中无法实例化,无法用@Autowired注入,因此在这使用BeanHelper加载bean
        redisUtil = BeanHelper.getBean(RedisUtil.class);
    }

    /**
     * 抓取页面元素
     * protected访问权限范围为：类内、同包、不同包子类
     * try(...){}的写法可以自动帮你释放()里的资源
     * @param url 链接
     * @return 页面内容
     */
    protected String crawl(String url){
        try (
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url))
        ) {
            //根据Response和页面编码格式获取页面result
            return EntityUtils.toString(httpResponse.getEntity(), (String) redisUtil.getHashKey(Constant.REDIS_NOVEL_SITE_CHARSET, NovelSiteEnum.getByUrl(url).getUrl()));
        } catch (Exception e) {
            throw new RuntimeException(e + " | 页面爬取失败!");
        }
    }

    /**
     * 根据下标获取选择器属性
     * @param url                链接
     * @param redisSelectorEnum  Redis选择器
     * @param index              下标
     * @return 选择器
     */
    public String getSelectorByIndex(String url, RedisSelectorEnum redisSelectorEnum, Integer index){
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
