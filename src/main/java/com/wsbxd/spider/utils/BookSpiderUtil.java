package com.wsbxd.spider.utils;

import com.wsbxd.spider.domain.po.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: 书籍爬虫工具类
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2019/2/20 14:11
 */
public class BookSpiderUtil {

    /**
     * 获取Map<Book.url,Book>
     * @param bookList  Book集合
     * @return  Map<Book.url,Book>
     */
    public static Map<String,Book> parseBookListByUrl(List<Book> bookList){
        return new HashMap<String,Book>(512){
            {
                for (Book book : bookList){
                    this.put(book.getUrl(),book);
                }
            }
        };
    }

    /**
     * 根据SiteId分类Book
     * @param bookList  Book集合
     * @return  Map<SiteId, List<Book>>
     */
    public static Map<Integer, List<Book>> bookListAccordSiteId(List<Book> bookList){
        return new HashMap<Integer, List<Book>>(16){
            {
                for (Book book : bookList){
                    Integer siteId = book.getSiteId();
                    if (this.containsKey(siteId)){
                        this.get(siteId).add(book);
                    }else{
                        this.put(siteId,new ArrayList<Book>(){{ add(book); }});
                    }
                }
            }
        };
    }

}
