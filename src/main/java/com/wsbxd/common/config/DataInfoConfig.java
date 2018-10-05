package com.wsbxd.common.config;

import com.wsbxd.common.constant.Constant;
import com.wsbxd.common.utils.RedisUtil;
import com.wsbxd.spider.domain.po.NovelSelector;
import com.wsbxd.spider.domain.po.Site;
import com.wsbxd.spider.service.INovelSelectorService;
import com.wsbxd.spider.service.ISiteService;
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

    @Autowired
    private ISiteService novelSiteService;

    @Autowired
    private INovelSelectorService novelSelectorService;

    @Autowired
    private RedisUtil redisUtil;

    private Integer mapSize = 2^3;

    @Override
    public void run(String... args) throws Exception {
        //添加小说站点到Redis
        addNovelSiteToRedis();
        //添加小说选择器到Redis
        addNovelSelectorToRedis();
    }

    /**
     * 添加小说选择器到Redis
     */
    private void addNovelSelectorToRedis() {
        List<NovelSelector> novelSelectors = novelSelectorService.findAll();
        Map<String,String> list = new HashMap<>(mapSize);
        Map<String,String> title = new HashMap<>(mapSize);
        Map<String,String> content = new HashMap<>(mapSize);
        Map<String,String> prev = new HashMap<>(mapSize);
        Map<String,String> next = new HashMap<>(mapSize);
        Map<String,String> book = new HashMap<>(mapSize);
        Map<String,String> nextBook = new HashMap<>(mapSize);
        for (NovelSelector novelSelector:novelSelectors) {
            list.put(novelSelector.getUrl(),novelSelector.getList());
            title.put(novelSelector.getUrl(),novelSelector.getTitle());
            content.put(novelSelector.getUrl(),novelSelector.getContent());
            prev.put(novelSelector.getUrl(),novelSelector.getPrev());
            next.put(novelSelector.getUrl(),novelSelector.getNext());
            book.put(novelSelector.getUrl(),novelSelector.getBook());
            nextBook.put(novelSelector.getUrl(),novelSelector.getNextBook());
        }
        //章节列表选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_LIST)){
            redisUtil.delete(Constant.REDIS_NOVEL_LIST);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_LIST,list);
        //小说标题选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_TITLE)){
            redisUtil.delete(Constant.REDIS_NOVEL_TITLE);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_TITLE,title);
        //小说内容选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_CONTENT)){
            redisUtil.delete(Constant.REDIS_NOVEL_CONTENT);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_CONTENT,content);
        //上一页选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_PREV)){
            redisUtil.delete(Constant.REDIS_NOVEL_PREV);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_PREV,prev);
        //下一页选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_NEXT)){
            redisUtil.delete(Constant.REDIS_NOVEL_NEXT);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_NEXT,next);
        //书籍选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_BOOK)){
            redisUtil.delete(Constant.REDIS_NOVEL_BOOK);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_BOOK,book);
        //下一本书籍选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_NEXT_BOOK)){
            redisUtil.delete(Constant.REDIS_NOVEL_NEXT_BOOK);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_NEXT_BOOK,nextBook);
    }

    /**
     * 添加小说站点到Redis
     */
    private void addNovelSiteToRedis() {
        //获取所有站点
        List<Site> sites = novelSiteService.findAll();
        //转换为map,key为链接,value为字符编码格式
        Map<String,String> map = sites.stream().collect(Collectors.toMap(Site::getUrl, Site::getCharset));
        if (redisUtil.isExists(Constant.REDIS_NOVEL_SITE_CHARSET)){
            redisUtil.delete(Constant.REDIS_NOVEL_SITE_CHARSET);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_SITE_CHARSET,map);
    }

}
