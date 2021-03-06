package com.wsbxd.spider.service.impl;

import com.github.pagehelper.PageInfo;
import com.wsbxd.common.utils.PageFactory;
import com.wsbxd.spider.domain.po.Book;
import com.wsbxd.spider.factory.BookSpiderFactory;
import com.wsbxd.spider.interfaces.IBookSpider;
import com.wsbxd.spider.mapper.BookMapper;
import com.wsbxd.spider.service.IBookService;
import com.wsbxd.spider.utils.BookSpiderUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * description: 书籍service实现类
 *
 * @author chenhaoxuan
 * @date 2018/10/3 14:52
 */
@Service
public class BookServiceImpl implements IBookService {

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public void insertOrUpdateAllBook(List<Book> books) {
        Map<Integer, List<Book>> siteIdAndBookList = BookSpiderUtil.bookListAccordSiteId(books);
        for (Map.Entry<Integer, List<Book>> entry : siteIdAndBookList.entrySet()){
            insertOrUpdateBookList(entry.getKey(),entry.getValue());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW ,rollbackFor = Exception.class)
    public void insertOrUpdateBookList(Integer siteId, List<Book> newBookList) {
        List<Book> oldBookList = bookMapper.selectBySiteId(siteId);
        Map<String, Book> newUrlAndBook = BookSpiderUtil.parseBookListByUrl(newBookList);
        Map<String, Book> oldUrlAndBook = BookSpiderUtil.parseBookListByUrl(oldBookList);
        List<Book> insertBooks = new ArrayList<>();
        List<Book> updateBooks = new ArrayList<>();
        for (String url : newUrlAndBook.keySet()){
            Book newBook = newUrlAndBook.get(url);
            Book oldBook = oldUrlAndBook.get(url);
            if (oldBook == null){
                insertBooks.add(newBook);
            }else if (!BookSpiderUtil.compareNewAndOldBook(newBook,oldBook)){
                updateBooks.add(newBook);
            }
        }
        bookMapper.insertList(insertBooks);
        bookMapper.updateList(updateBooks);
    }

    @Override
    public PageInfo<Book> getBooks(String property, String value) {
        //分页方法
        PageFactory.defaultPage();
        Example example = new Example(Book.class);
        if (StringUtils.isNotBlank(property) && StringUtils.isNotBlank(value)){
            example.createCriteria().andEqualTo(property,value);
        }
        List<Book> books = bookMapper.selectByExample(example);
        return new PageInfo<>(books);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertBooks(String url, Integer maxTryNum) {
        try {
            IBookSpider bookSpider = BookSpiderFactory.getBookSpider(url);
            Iterator<List<Book>> iterator = bookSpider.iterator(url, maxTryNum);
            while (iterator.hasNext()) {
                List<Book> books = iterator.next();
                bookMapper.insertList(books);
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Integer getIdByUrl(String url) {
        return bookMapper.getIdByUrl(url);
    }
}
