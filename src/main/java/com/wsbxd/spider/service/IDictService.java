package com.wsbxd.spider.service;

import com.wsbxd.spider.domain.po.Dict;

import java.util.List;

/**
 * description: 字典service
 *
 * @author chenhaoxuan
 * @date 2018/10/13 19:44
 */
public interface IDictService {

    /**
     * 添加字典数据
     * @param name      名称
     * @param mapName   映射名称
     * @param pid       父id
     * @return  是否添加成功
     */
    boolean insertDict(String name, String mapName, Integer pid);

    /**
     * 根据pid查询字典
     * @param pid
     * @return
     */
    List<Dict> selectByPid(Integer pid);

}
