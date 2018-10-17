package com.wsbxd.common.config;

import com.wsbxd.common.constant.Constant;
import com.wsbxd.common.utils.RedisUtil;
import com.wsbxd.spider.domain.po.Dict;
import com.wsbxd.spider.domain.po.NovelSelector;
import com.wsbxd.spider.domain.po.Site;
import com.wsbxd.spider.service.IDictService;
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

    private final ISiteService siteService;

    private final INovelSelectorService novelSelectorService;

    private final IDictService dictService;

    private final RedisUtil redisUtil;

    @Autowired
    public DataInfoConfig(ISiteService siteService, INovelSelectorService novelSelectorService, IDictService dictService, RedisUtil redisUtil) {
        this.siteService = siteService;
        this.novelSelectorService = novelSelectorService;
        this.dictService = dictService;
        this.redisUtil = redisUtil;
    }

    private final static Integer MAP_SIZE = 2^3;

    @Override
    public void run(String... args) {
        //添加小说站点到Redis
        addNovelSiteToRedis();
        //添加小说选择器到Redis
        addNovelSelectorToRedis();
        //添加小说状态到Redis
        addNovelStatus();
        //添加小说类型到Redis
        addNovelType();
    }

    /**
     * 添加小说类型到Redis
     */
    private void addNovelType() {
        List<Dict> dicts = dictService.selectByPid(5);
        Map<String,String> map = new HashMap<>(16);
        for (Dict dict:dicts) {
            map.put(dict.getName(),dict.getMapName());
        }
        if (redisUtil.isExists(Constant.REDIS_DICT_TYPR)){
            redisUtil.delete(Constant.REDIS_DICT_TYPR);
        }
        redisUtil.putAll(Constant.REDIS_DICT_TYPR,map);
    }

    /**
     * 添加小说类型到Redis
     */
    private void addNovelStatus() {
        List<Dict> dicts = dictService.selectByPid(1);
        Map<String,String> map = new HashMap<>(16);
        for (Dict dict:dicts) {
            map.put(dict.getName(),dict.getMapName());
        }
        if (redisUtil.isExists(Constant.REDIS_DICT_STATUS)){
            redisUtil.delete(Constant.REDIS_DICT_STATUS);
        }
        redisUtil.putAll(Constant.REDIS_DICT_STATUS,map);
    }

    /**
     * 添加小说选择器到Redis
     */
    private void addNovelSelectorToRedis() {
        List<NovelSelector> novelSelectors = novelSelectorService.selectAll();
        Map<String,String> list = new HashMap<>(MAP_SIZE);
        Map<String,String> title = new HashMap<>(MAP_SIZE);
        Map<String,String> content = new HashMap<>(MAP_SIZE);
        Map<String,String> prev = new HashMap<>(MAP_SIZE);
        Map<String,String> next = new HashMap<>(MAP_SIZE);
        Map<String,String> book = new HashMap<>(MAP_SIZE);
        Map<String,String> nextPage = new HashMap<>(MAP_SIZE);
        Map<String,String> type = new HashMap<>(MAP_SIZE);
        for (NovelSelector novelSelector:novelSelectors) {
            list.put(novelSelector.getUrl(),novelSelector.getList());
            title.put(novelSelector.getUrl(),novelSelector.getTitle());
            content.put(novelSelector.getUrl(),novelSelector.getContent());
            prev.put(novelSelector.getUrl(),novelSelector.getPrev());
            next.put(novelSelector.getUrl(),novelSelector.getNext());
            book.put(novelSelector.getUrl(),novelSelector.getBook());
            nextPage.put(novelSelector.getUrl(),novelSelector.getNextPage());
            type.put(novelSelector.getUrl(),novelSelector.getType());
        }
        //章节列表选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_LIST_SELECT)){
            redisUtil.delete(Constant.REDIS_NOVEL_LIST_SELECT);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_LIST_SELECT,list);
        //小说标题选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_TITLE_SELECT)){
            redisUtil.delete(Constant.REDIS_NOVEL_TITLE_SELECT);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_TITLE_SELECT,title);
        //小说内容选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_CONTENT_SELECT)){
            redisUtil.delete(Constant.REDIS_NOVEL_CONTENT_SELECT);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_CONTENT_SELECT,content);
        //上一页选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_PREV_SELECT)){
            redisUtil.delete(Constant.REDIS_NOVEL_PREV_SELECT);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_PREV_SELECT,prev);
        //下一页选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_NEXT_SELECT)){
            redisUtil.delete(Constant.REDIS_NOVEL_NEXT_SELECT);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_NEXT_SELECT,next);
        //书籍选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_BOOK_SELECT)){
            redisUtil.delete(Constant.REDIS_NOVEL_BOOK_SELECT);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_BOOK_SELECT,book);
        //下一页书籍列表选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_NEXT_BOOK_SELECT)){
            redisUtil.delete(Constant.REDIS_NOVEL_NEXT_BOOK_SELECT);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_NEXT_BOOK_SELECT,nextPage);
        //书籍类型选择器
        if (redisUtil.isExists(Constant.REDIS_NOVEL_TYPE_SELECT)){
            redisUtil.delete(Constant.REDIS_NOVEL_TYPE_SELECT);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_TYPE_SELECT,type);
    }

    /**
     * 添加小说站点到Redis
     */
    private void addNovelSiteToRedis() {
        //获取所有站点
        List<Site> sites = siteService.selectSiteAll();
        //转换为map,key为链接,value为字符编码格式
        Map<String,String> map = sites.stream().collect(Collectors.toMap(Site::getUrl, Site::getCharset));
        if (redisUtil.isExists(Constant.REDIS_NOVEL_SITE_CHARSET_SELECT)){
            redisUtil.delete(Constant.REDIS_NOVEL_SITE_CHARSET_SELECT);
        }
        redisUtil.putAll(Constant.REDIS_NOVEL_SITE_CHARSET_SELECT,map);
    }

}
