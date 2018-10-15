package com.wsbxd.spider.controller;

import com.github.pagehelper.PageInfo;
import com.wsbxd.common.admin.po.BusinessMsg;
import com.wsbxd.common.utils.BusinessCode;
import com.wsbxd.spider.domain.po.Book;
import com.wsbxd.spider.factory.ChapterSpiderFactory;
import com.wsbxd.spider.factory.ContentSpiderFactory;
import com.wsbxd.spider.service.IBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "测试json", notes = "测试json")
    @RequestMapping(value = "testJson", method = RequestMethod.POST)
    public String testJson(@RequestBody String[] strings){
        System.out.println(strings);
        return null;
    }

}
