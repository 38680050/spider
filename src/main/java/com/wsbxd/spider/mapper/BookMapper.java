package com.wsbxd.spider.mapper;

import com.wsbxd.common.utils.TkMapper;
import com.wsbxd.spider.domain.po.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 书籍mapper
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 11:41
 */
@Mapper
@Repository("bookMapper")
public interface BookMapper extends TkMapper<Book> {

    /**
     * 根据链接获取书籍id
     * @param url   链接
     * @return  书籍id
     */
    Integer getIdByUrl(String url);

    /**
     * 根据站点id查询BookList
     * @param siteId    站点id
     * @return  List<Book>
     */
    List<Book> selectBySiteId(Integer siteId);
}
