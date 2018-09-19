package com.wsbxd.spider.mapper;

import com.wsbxd.common.utils.TkMapper;
import com.wsbxd.spider.domain.po.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * description: 图书mapper
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 11:41
 */
@Mapper
@Repository
public interface BookMapper extends TkMapper<Book> {



}
