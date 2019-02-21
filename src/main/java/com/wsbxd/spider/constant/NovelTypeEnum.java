package com.wsbxd.spider.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description: 小说类型枚举
 *
 * @author chenhaoxuan
 * @date 2019/2/21 21:41
 */
@Getter
@AllArgsConstructor
public enum NovelTypeEnum {

    /**
     * 小说类型
     */
    XHQH(1,"玄幻奇幻"),
    WXXZ(2,"武侠修真"),
    DSYQ(3,"都市言情"),
    LSJS(4,"历史军事"),
    YXJJ(5,"游戏竞技"),
    KHLY(6,"科幻灵异"),
    SMXS(7,"耽美小说"),
    TRXS(8,"同人小说"),
    QTLX(9,"其他类型"),
    ;

    private Integer id;

    private String name;

    /**
     * 根据 name 获取 NovelTypeEnum
     * @param name    小说类型名称
     * @return  NovelTypeEnum
     */
    public static NovelTypeEnum getById(String name){
        for (NovelTypeEnum novelTypeEnum : values()){
            if (novelTypeEnum.name.equals(name)){
                return novelTypeEnum;
            }
        }
        throw new RuntimeException("小说类型枚举未发现 name = " + name);
    }

    /**
     * 根据 id 获取 NovelTypeEnum
     * @param id    id
     * @return  NovelTypeEnum
     */
    public static NovelTypeEnum getById(Integer id){
        for (NovelTypeEnum novelTypeEnum : values()){
            if (novelTypeEnum.id.equals(id)){
                return novelTypeEnum;
            }
        }
        throw new RuntimeException("小说类型枚举未发现 id = " + id);
    }

}
