package com.wsbxd.common.utils;

import com.wsbxd.common.constant.Constant;
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
    LIST(1,"list", Constant.REDIS_NOVEL_LIST_SELECT),
    /**
     * 章节标题选择器
     */
    TITLE(2,"title",Constant.REDIS_NOVEL_TITLE_SELECT),
    /**
     * 章节内容选择器
     */
    CONTENT(3,"content",Constant.REDIS_NOVEL_CONTENT_SELECT),
    /**
     * 上一章选择器
     */
    PREV(4,"prev",Constant.REDIS_NOVEL_PREV_SELECT),
    /**
     * 下一章选择器
     */
    NEXT(5,"next",Constant.REDIS_NOVEL_NEXT_SELECT),
    /**
     * 书籍选择器选择器
     */
    BOOK(6,"book",Constant.REDIS_NOVEL_BOOK_SELECT),
    /**
     * 下一本书籍选择器
     */
    NEXT_BOOK(7,"nextPage",Constant.REDIS_NOVEL_NEXT_BOOK_SELECT),
    /**
     * 书籍类型选择器
     */
    TYPE(8,"type",Constant.REDIS_NOVEL_TYPE_SELECT);

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
