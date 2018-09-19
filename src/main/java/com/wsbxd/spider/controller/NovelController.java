package com.wsbxd.spider.controller;

import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.factory.ChapterSpiderFactory;
import com.wsbxd.spider.interfaces.IChapterSpider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/1 15:42
 */
@Controller
@RequestMapping("spider")
@Api(value = "小说API")
public class NovelController {

    @ApiOperation(value = "爬取小说章节列表", notes = "爬取小说章节列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "要爬取的链接", required = true, dataType = "String", paramType = "query", defaultValue = "https://www.11kt.cn/read/72453/index.html")
    })
    @RequestMapping(value = "crawl",method = RequestMethod.GET)
    public void crawl(@RequestParam(value = "url")String url){
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapters = chapterSpider.getChapters(url);
        for (Chapter chapter:chapters){
            System.out.println(chapter);
        }
    }

}
