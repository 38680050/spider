package com.wsbxd.spider.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description: 站点枚举
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2019/2/20 14:18
 */
@Getter
@AllArgsConstructor
public enum SiteEnum {

    /**
     * 站点
     */
    KSZ(1,"看书中","GBK","kanshuzhong.com","http://www.kanshuzhong.com/"),
    BXWF(2,"笔下文学","GBK","bxwx.us","http://www.bxwx.us/");

    private Integer id;

    private String siteName;

    private String charset;

    private String url;

    private String fullUrl;

    /**
     * 根绝fullUrl获取Site
     * @param fullUrl    fullUrl
     * @return  SiteEnum
     */
    public static SiteEnum getByFullUrl(String fullUrl){
        for (SiteEnum siteEnum : values()){
            if (siteEnum.fullUrl.equals(fullUrl)){
                return siteEnum;
            }
        }
        throw new RuntimeException("站点枚举未发现 fullUrl = " + fullUrl);
    }

    /**
     * 根绝url获取Site
     * @param url    url
     * @return  SiteEnum
     */
    public static SiteEnum getByUrl(String url){
        for (SiteEnum siteEnum : values()){
            if (siteEnum.url.equals(url)){
                return siteEnum;
            }
        }
        throw new RuntimeException("站点枚举未发现 url = " + url);
    }

    /**
     * 根绝id获取Site
     * @param id    id
     * @return  SiteEnum
     */
    public static SiteEnum getById(Integer id){
        for (SiteEnum siteEnum : values()){
            if (siteEnum.id.equals(id)){
                return siteEnum;
            }
        }
        throw new RuntimeException("站点枚举未发现 id = " + id);
    }
}
