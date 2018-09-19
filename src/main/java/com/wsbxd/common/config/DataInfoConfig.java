package com.wsbxd.common.config;

import com.wsbxd.common.constant.Constant;
import com.wsbxd.common.utils.RedisUtil;
import com.wsbxd.spider.domain.po.NovelSelector;
import com.wsbxd.spider.domain.po.NovelSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: 数据初始化配置类
 *
 * @author chenhaoxuan
 * @date 2018/9/15 11:15
 */
@Component
public class DataInfoConfig implements CommandLineRunner {

    /*@Autowired
    private INovelSiteService novelSiteService;

    @Autowired
    private INovelSelectorService novelSelectorService;*/

    @Autowired
    private RedisUtil redisUtil;

    private Integer mapSize = 2^3;

    @Override
    public void run(String... args) throws Exception {
        //添加小说站点到Redis
        //addNovelSiteToRedis();
        //添加小说选择器到Redis
        //addNovelSelectorToRedis();
    }

    /**
     * 添加小说选择器到Redis
     */
    /*private void addNovelSelectorToRedis() {
        List<NovelSelector> novelSelectors = novelSelectorService.findAll();
        Map<String,String> list = new HashMap<>(mapSize);
        Map<String,String> title = new HashMap<>(mapSize);
        Map<String,String> content = new HashMap<>(mapSize);
        Map<String,String> prev = new HashMap<>(mapSize);
        Map<String,String> next = new HashMap<>(mapSize);
        for (NovelSelector novelSelector:novelSelectors) {
            list.put(novelSelector.getUrl(),novelSelector.getList());
            title.put(novelSelector.getUrl(),novelSelector.getTitle());
            content.put(novelSelector.getUrl(),novelSelector.getContent());
            prev.put(novelSelector.getUrl(),novelSelector.getPrev());
            next.put(novelSelector.getUrl(),novelSelector.getNext());
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_LIST,list);
        redisUtil.putAll(Constant.REDIS_NOVEL_TITLE,title);
        redisUtil.putAll(Constant.REDIS_NOVEL_CONTENT,content);
        redisUtil.putAll(Constant.REDIS_NOVEL_PREV,prev);
        redisUtil.putAll(Constant.REDIS_NOVEL_NEXT,next);
    }

    *//**
     * 添加小说站点到Redis
     *//*
    private void addNovelSiteToRedis() {
        //获取所有站点
        List<NovelSite> novelSites = novelSiteService.findAll();
        //转换为map,key为链接,value为字符编码格式
        Map<String,String> map = novelSites.stream().collect(Collectors.toMap(NovelSite::getUrl,NovelSite::getCharset));
        redisUtil.putAll(Constant.REDIS_NOVEL_SITE_CHARSET,map);
    }*/

}
