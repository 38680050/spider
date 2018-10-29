package com.wsbxd.spider.mapper;

import com.wsbxd.common.utils.TkMapper;
import com.wsbxd.spider.domain.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 小说类型mapper
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2018/10/6 11:57
 */
@Mapper
@Repository("typeMapper")
public interface TypeMapper extends TkMapper<Type> {

    /**
     * 根据站点URL查询小说类型集合
     * @param siteUrl   链接
     * @return  小说类型集合
     */
    List<Type> selectBySiteUrl(@Param("siteUrl") String siteUrl);

    /**
     * 根据siteUrl删除小说类型
     * @param siteUrl   站点链接
     */
    void deleteTypeBySiteUrl(@Param("siteUrl")String siteUrl);

    /**
     * 根据链接获取小说类型
     * @param url   链接
     * @return  小说类型
     */
    Type selectTypeByUrl(String url);
}
