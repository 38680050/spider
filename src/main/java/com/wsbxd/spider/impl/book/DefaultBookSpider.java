package com.wsbxd.spider.impl.book;

import com.wsbxd.spider.domain.po.Book;

import java.util.List;

/**
 * description: 默认图书爬虫
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/20 14:41
 */
public class DefaultBookSpider extends AbstractBookSpider {

    @Override
    public List<Book> getBooks(String url, Integer maxTryNum) {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return null;
    }

    @Override
    public Iterable<List<Book>> iterator(String firstPage, Integer maxTryNum) {
        return null;
    }
}
