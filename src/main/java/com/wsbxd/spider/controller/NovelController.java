package com.wsbxd.spider.controller;

import com.wsbxd.spider.service.IChapterService;
import com.wsbxd.spider.service.IContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/1 15:42
 */
@Controller
@RequestMapping("spider")
public class NovelController {

    @Autowired
    private IChapterService chapterService;

    @Autowired
    private IContextService contextService;

}
