package com.wsbxd.common.config;

import com.wsbxd.spider.domain.po.Site;
import com.wsbxd.spider.service.IBookService;
import com.wsbxd.spider.service.IChapterService;
import com.wsbxd.spider.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description: 定时任务配置
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2018/10/5 10:14
 */
@Component
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
     * 定时任务,每天临晨6点触发一次
     */
    @Scheduled(cron = "0 0 6 * * ?")
    public void updateNovel(){
        List<Site> sites = siteService.selectSiteAll();
        
    }

}
