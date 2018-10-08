package com.wsbxd.spider.interfaces;

import com.wsbxd.spider.domain.po.Book;

import java.util.Iterator;
import java.util.List;

/**
 * description: 书籍爬取接口
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 16:06
 */
public interface IBookSpider {

    /**
     * 根据链接抓取书籍集合
     * @param url           链接
     * @param maxTryNum     最大尝试次数
     * @return  书籍集合
     */
    List<Book> crawlBooks(String url, Integer maxTryNum);

    /**
     * 是否有下一章
     * @return boolean
     */
    boolean hasNext();

    /**
     * 获取下一章
     * @return 下一章
     */
    String next();

    /**
     * 迭代器
     * @param firstPage 首页
     * @param maxTryNum 最大尝试次数
     * @return 迭代器
     */
    Iterator<List<Book>> iterator(String firstPage, Integer maxTryNum);

}
