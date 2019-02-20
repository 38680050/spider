package com.wsbxd.spider.service;

import com.github.pagehelper.PageInfo;
import com.wsbxd.spider.domain.po.Book;
import com.wsbxd.spider.domain.po.Site;

import java.util.List;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/10/3 14:51
 */
public interface IBookService {

    void insertOrUpdateAllBook(List<Book> books);

    void insertOrUpdateBookList(Site site, List<Book> books);

    /**
     * 根据查询条件获取书籍
     * @param property 属性
     * @param value    值
     * @return  书籍集合
     */
    PageInfo<Book> getBooks(String property, String value);

    /**
     * 添加书籍
     * @param url           链接
     * @param maxTryNum     最大尝试数
     * @return  添加成功或失败
     */
    boolean insertBooks(String url,Integer maxTryNum);

    /**
     * 根据链接获取书籍id
     * @param url   链接
     * @return  书籍id
     */
    Integer getIdByUrl(String url);

}
