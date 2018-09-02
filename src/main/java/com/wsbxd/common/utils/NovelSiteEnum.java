package com.wsbxd.common.utils;

import lombok.Getter;

/**
 * description: 支持的小说网站枚举
 *
 * @author chenhaoxuan
 * @date 2018/9/2 11:25
 */
@Getter
public enum NovelSiteEnum {

    /**
     * 起点小说
     */
    DingDianXiaoShuo(1,"11kt.cn"),
    /**
     * 笔趣阁
     */
    BiQuGe(2,"xs.la");

    /**
     * 编号
     */
    private Integer id;
    /**
     * 链接
     */
    private String url;

    /**
     * 根据id获取对应的小说站点枚举
     * @param id 编号
     * @return
     */
    public static NovelSiteEnum getById(Integer id){
        switch (id){
            case 1:return DingDianXiaoShuo;
            case 2:return BiQuGe;
            default:throw new RuntimeException("id = " + id +"的小说网站未被支持");
        }
    }

    /**
     * 根据链接获取对应的小说站点枚举
     * @param url 链接
     * @return
     */
    public static NovelSiteEnum getByUrl(String url){
        for (NovelSiteEnum novelSiteEnum:values()){
            if(url.contains(novelSiteEnum.url)) {
                return novelSiteEnum;
            }
        }
        throw new RuntimeException("url = " + url + "的小说网站未被支持");
    }

    /**
     * 隐藏构造器
     * @param id 编号
     * @param url 链接
     */
    private NovelSiteEnum(Integer id, String url) {
        this.id = id;
        this.url = url;
    }
}
