package com.wsbxd.spider.mapper;

import com.wsbxd.common.utils.TkMapper;
import com.wsbxd.spider.domain.po.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 字典mapper
 *
 * @author chenhaoxuan
 * @date 2018/10/13 19:45
 */
@Mapper
@Repository("dictMapper")
public interface DictMapper extends TkMapper<Dict> {

    /**
     * 根据pid查询字典
     * @param pid   父id
     * @return  字典集合
     */
    List<Dict> selectByPid(Integer pid);

}
