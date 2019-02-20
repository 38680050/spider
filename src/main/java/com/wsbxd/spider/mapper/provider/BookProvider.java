package com.wsbxd.spider.mapper.provider;

import com.wsbxd.common.utils.SqlProviderUtil;
import com.wsbxd.spider.domain.po.Book;

import java.util.List;

/**
 * description: Book Provider
 *
 * @author chenhaoxuan
 * @date 2019/2/20 21:37
 */
public class BookProvider {

    private final static String SQL = "id,url,title,author,type,lastUpdateTime,lastUpdateContent,lastUpdateContentUrl,status,addTime,updateTime,siteId";

    public String updateList(List<Book> bookList){
        return " update t_book " + SqlProviderUtil.updateListJoint(bookList) + " where " +  SqlProviderUtil.inJoint("id",SqlProviderUtil.getIdList(bookList),false);
    }

}
