package com.wsbxd.spider.interfaces;

import com.wsbxd.spider.domain.po.Book;

import java.util.List;

/**
 * description: 图书爬取接口
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 16:06
 */
public interface IBookSpider {

    /**
     * 根据链接获取图书集合
     * @param url           链接
     * @param maxTryNum     最大尝试次数
     * @return  图书集合
     */
    List<Book> getBooks(String url,Integer maxTryNum);

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
    Iterable<List<Book>> iterator(String firstPage, Integer maxTryNum);

}
