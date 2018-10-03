package com.wsbxd.spider.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.wsbxd.common.admin.po.BusinessMsg;
import com.wsbxd.common.utils.BusinessCode;
import com.wsbxd.common.utils.PageFactory;
import com.wsbxd.spider.domain.po.Book;
import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.domain.po.Content;
import com.wsbxd.spider.factory.ChapterSpiderFactory;
import com.wsbxd.spider.factory.ContentSpiderFactory;
import com.wsbxd.spider.service.IBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: 小说Controller
 *
 * @author chenhaoxuan
 * @date 2018/9/1 15:42
 */
@RestController
@RequestMapping("spider")
@Api(value = "小说API", tags = {"小说API"})
public class NovelController {

    @Autowired
    private IBookService bookService;

    @RequestMapping(value = "getBooks",method = RequestMethod.POST)
    public BusinessMsg getBooks(
            @RequestParam(value = "property",required = false)String property,
            @RequestParam(value = "value",required = false)String value
    ){
        PageInfo<Book> books = bookService.getBooks(property,value);
        return new BusinessMsg(books);
    }

    @ApiOperation(value = "爬取小说章节列表", notes = "爬取小说章节列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "要爬取的链接", required = true, dataType = "String", paramType = "query", defaultValue = "https://www.11kt.cn/read/72453/index.html")
    })
    @RequestMapping(value = "crawlChapterList",method = RequestMethod.GET)
    public BusinessMsg crawlChapterList(@RequestParam(value = "url")String url){
        return new BusinessMsg(BusinessCode.GLOBAL_SUCCESS,ChapterSpiderFactory.getChapterSpider(url).getChapters(url));
    }

    @ApiOperation(value = "爬取小说实体内容", notes = "爬取小说实体内容数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "要爬取的链接", required = true, dataType = "String", paramType = "query", defaultValue = "https://www.11kt.cn/read/72453/33962963.html")
    })
    @RequestMapping(value = "crawlContent",method = RequestMethod.GET)
    public BusinessMsg crawlContent(@RequestParam(value = "url")String url){
        return new BusinessMsg(BusinessCode.GLOBAL_SUCCESS,ContentSpiderFactory.getContentSpider(url).getContent(url));
    }

}
