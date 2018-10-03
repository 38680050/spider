package com.wsbxd.spider.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.wsbxd.common.utils.PageFactory;
import com.wsbxd.spider.domain.po.Book;
import com.wsbxd.spider.mapper.BookMapper;
import com.wsbxd.spider.service.IBookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * description: 书籍service实现类
 *
 * @author chenhaoxuan
 * @date 2018/10/3 14:52
 */
@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public PageInfo<Book> getBooks(String property, String value) {
        PageFactory.defaultPage();
        Example example = new Example(Book.class);
        if (StringUtils.isNotBlank(property) && StringUtils.isNotBlank(value)){
            example.createCriteria().andEqualTo(property,value);
        }
        List<Book> books = bookMapper.selectByExample(example);
        return new PageInfo<>(books);
    }
}
