package com.wsbxd.spider.utils;

import com.wsbxd.common.utils.ReflectUtil;
import com.wsbxd.spider.domain.po.Book;

import java.lang.reflect.Field;
import java.util.*;

/**
 * description: 书籍爬虫工具类
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2019/2/20 14:11
 */
public class BookSpiderUtil {

    /**
     * BOOK 字段集合
     */
    private final static List<String> BOOK_FIELD_LIST = new ArrayList<String>(){{
        Field[] fields = Book.class.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> this.add(field.getName()));
    }};

    /**
     * 比较新旧Book,如果新Book与旧Book字段的值,如果一样,则将新Book的字段值设为空
     * @param newBook   新Book
     * @param oldBook   旧Book
     * @return 完全一样则返回true
     */
    public static boolean compareNewAndOldBook(Book newBook, Book oldBook){
        boolean flag = true;
        Class<? extends Book> newBookClass = newBook.getClass();
        Class<? extends Book> oldBookClass = oldBook.getClass();
        for (String field : BOOK_FIELD_LIST){
            Object newValue = ReflectUtil.getValue(newBook, newBookClass, field);
            Object oldValue = ReflectUtil.getValue(oldBook, oldBookClass, field);
            if (newValue.equals(oldValue)){
                ReflectUtil.setValue(newBook,newBookClass,field,null);
            }else{
                flag = false;
            }
        }
        return flag;
    }

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
