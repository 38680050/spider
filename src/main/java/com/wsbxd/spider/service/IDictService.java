package com.wsbxd.spider.service;

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
     * @param type      类型
     * @param pid       父id
     * @return  是否添加成功
     */
    boolean insertDict(String name, String mapName, String type, Integer pid);

}
