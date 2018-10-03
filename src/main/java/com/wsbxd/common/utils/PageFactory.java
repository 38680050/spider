package com.wsbxd.common.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * description: 分页工具类
 *
 * @author chenhaoxuan
 * @date 2018/10/3 14:59
 */
public class PageFactory {

    public static Page defaultPage(){
        HttpServletRequest request = HttpKit.getRequest();
        //当前页码
        int pageNum = 1;
        String pageStr = request.getParameter("page");
        if (StringUtils.isNotBlank(pageStr) && StringUtils.isNumeric(pageStr)){
            pageNum = Integer.valueOf(pageStr);
        }
        //每页多少条数据
        int limit = 10;
        String limitStr = request.getParameter("limit");
        if (StringUtils.isNotBlank(limitStr) && StringUtils.isNumeric(limitStr)){
            limit = Integer.valueOf(limitStr);
        }
        //分页
        return PageHelper.startPage(pageNum, limit);
    }

}
