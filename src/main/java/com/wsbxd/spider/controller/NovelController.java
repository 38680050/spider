package com.wsbxd.spider.controller;

import com.github.pagehelper.PageInfo;
import com.wsbxd.common.admin.po.BusinessMsg;
import com.wsbxd.common.utils.BusinessCode;
import com.wsbxd.spider.domain.po.Book;
import com.wsbxd.spider.service.*;
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

    private final ISiteService siteService;

    private final IBookService bookService;

    private final IChapterService chapterService;

    private final IDictService dictService;

    private final INovelSelectorService novelSelectorService;

    @Autowired
    public NovelController(IBookService bookService, ISiteService siteService, IChapterService chapterService, IDictService dictService, INovelSelectorService novelSelectorService) {
        this.bookService = bookService;
        this.siteService = siteService;
        this.chapterService = chapterService;
        this.dictService = dictService;
        this.novelSelectorService = novelSelectorService;
    }

    @ApiOperation(value = "获取书籍列表", notes = "根据条件获取书籍列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "limit", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "property", value = "属性名称", dataType = "String",paramType = "query",defaultValue = "title"),
            @ApiImplicitParam(name = "value", value = "查询值", dataType = "String",paramType = "query",defaultValue = "恶魔")
    })
    @RequestMapping(value = "getBooks",method = RequestMethod.POST)
    public BusinessMsg getBooks(
            @RequestParam(value = "property",required = false)String property,
            @RequestParam(value = "value",required = false)String value
    ){
        PageInfo<Book> books = bookService.getBooks(property,value);
        return new BusinessMsg(books);
    }

    @ApiOperation(value = "添加字典数据", notes = "添加字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "mapName", value = "映射名称",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pid", value = "父id",required = true, dataType = "int",paramType = "query",example = "1")
    })
    @RequestMapping(value = "addDict",method = RequestMethod.POST)
    public BusinessMsg addDict(
            @RequestParam(value = "name")String name,
            @RequestParam(value = "mapName")String mapName,
            @RequestParam(value = "type")String type,
            @RequestParam(value = "pid")Integer pid
    ){
        boolean flag = dictService.insertDict(name,mapName,type,pid);
        return flag ? new BusinessMsg() : new BusinessMsg(BusinessCode.GLOBAL_ERROR);
    }

    @ApiOperation(value = "测试json", notes = "测试json")
    @RequestMapping(value = "testJson", method = RequestMethod.POST)
    public String testJson(@RequestBody String[] strings){
        System.out.println(strings);
        return null;
    }

}
