package com.wsbxd.spider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * description: 页面跳转Controller
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/20 11:05
 */
@Controller
public class PageController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
