package com.wsbxd.spider.controller;

import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.factory.ChapterSpiderFactory;
import com.wsbxd.spider.interfaces.IChapterSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/1 15:42
 */
@Controller
@RequestMapping("spider")
public class NovelController {

    @RequestMapping("crawl")
    public void crawl(String url){
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapters = chapterSpider.getChapters(url);
        for (Chapter chapter:chapters){
            System.out.println(chapter);
        }
    }

}
