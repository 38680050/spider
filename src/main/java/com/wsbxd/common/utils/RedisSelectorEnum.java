package com.wsbxd.common.utils;

import lombok.Getter;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/15 18:10
 */
@Getter
public enum RedisSelectorEnum {

    /**
     * 章节列表选择器
     */
    LIST(1,"list","spider:novel-list-all"),
    /**
     * 章节列表选择器
     */
    TITLE(2,"title","spider:novel-title-all"),
    /**
     * 章节列表选择器
     */
    CONTENT(3,"content","spider:novel-content-all"),
    /**
     * 章节列表选择器
     */
    PREV(4,"prev","spider:novel-prev-all"),
    /**
     * 章节列表选择器
     */
    NEXT(5,"next","spider:novel-next-all");

    /**
     * id
     */
    private Integer id;
    /**
     * 选择器名称
     */
    private String name;
    /**
     * Redis中的名称
     */
    private String redisName;

    /**
     * 根据选择器名称获取选择器
     * @param name  选择器名称
     * @return 选择器
     */
    public static RedisSelectorEnum getByName(String name){
        for (RedisSelectorEnum redisSelectorEnum:values()) {
            if (redisSelectorEnum.name.equals(name)){
                return redisSelectorEnum;
            }
        }
        throw new RuntimeException("name = " + name + " 的Redis选择器获取失败!");
    }

    /**
     * 私有化构造器
     * @param id            id
     * @param name          构造器名称
     * @param redisName     Redis中的名称
     */
    private RedisSelectorEnum(Integer id, String name, String redisName) {
        this.id = id;
        this.name = name;
        this.redisName = redisName;
    }
}
