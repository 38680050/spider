package com.wsbxd.spider.service;

import com.github.pagehelper.PageInfo;
import com.wsbxd.spider.domain.po.Book;

import java.util.List;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/10/3 14:51
 */
public interface IBookService {

    /**
     * 根据查询条件获取书籍
     * @param property 属性
     * @param value    值
     * @return  书籍集合
     */
    PageInfo<Book> getBooks(String property, String value);

}
