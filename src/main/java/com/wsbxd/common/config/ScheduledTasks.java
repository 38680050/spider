package com.wsbxd.common.config;

import com.wsbxd.spider.constant.SiteEnum;
import com.wsbxd.spider.domain.po.Book;
import com.wsbxd.spider.domain.po.Type;
import com.wsbxd.spider.factory.BookSpiderFactory;
import com.wsbxd.spider.interfaces.IBookSpider;
import com.wsbxd.spider.service.IBookService;
import com.wsbxd.spider.service.IChapterService;
import com.wsbxd.spider.service.ISiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * description: 定时任务配置
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2018/10/5 10:14
 */
@Component
@Slf4j
public class ScheduledTasks {

    private final ISiteService siteService;

    private final IBookService bookService;

    private final IChapterService chapterService;

    @Autowired
    public ScheduledTasks(ISiteService siteService, IBookService bookService, IChapterService chapterService) {
        this.siteService = siteService;
        this.bookService = bookService;
        this.chapterService = chapterService;
    }

    /**
     * 定时任务,每天临晨3点触发一次
     */
    @Scheduled(cron = "0 20 10 * * ?")
    public void updateNovel(){
        log.info("笔下文学爬取开始");
        long start = System.currentTimeMillis();
        Integer id = SiteEnum.BXWF.getId();
        List<Type> types = siteService.selectTypesBySiteId(id);
        List<Book> bookList = new ArrayList<>();
        for (Type type : types){
            IBookSpider spider = BookSpiderFactory.getBookSpider(type.getUrl());
            Iterator<List<Book>> iterator = spider.iterator(type.getUrl(), 3);
            while (iterator.hasNext()) {
                List<Book> books = iterator.next();
                log.info("当前爬取 {} 类型,本页共 {} 本",type.getName(),books.size());
                bookList.addAll(books);
            }
        }
        bookService.insertOrUpdateBookList(id,bookList);
        log.info("笔下文学图书共 {} 本,用时 {} 毫秒!",bookList.size(),(System.currentTimeMillis() - start));
    }

}
