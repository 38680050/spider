package com.wsbxd.spider.constant;

import lombok.Getter;

/**
 * description: 站点枚举
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2019/2/20 14:18
 */
@Getter
public enum Site {

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
     * @return  Site
     */
    public static Site getByFullUrl(String fullUrl){
        for (Site site : values()){
            if (site.fullUrl.equals(fullUrl)){
                return site;
            }
        }
        throw new RuntimeException("站点枚举未发现 fullUrl = " + fullUrl);
    }

    /**
     * 根绝url获取Site
     * @param url    url
     * @return  Site
     */
    public static Site getByUrl(String url){
        for (Site site : values()){
            if (site.url.equals(url)){
                return site;
            }
        }
        throw new RuntimeException("站点枚举未发现 url = " + url);
    }

    /**
     * 根绝id获取Site
     * @param id    id
     * @return  Site
     */
    public static Site getById(Integer id){
        for (Site site : values()){
            if (site.id.equals(id)){
                return site;
            }
        }
        throw new RuntimeException("站点枚举未发现 id = " + id);
    }

    Site(Integer id, String siteName, String charset, String url, String fullUrl) {
        this.id = id;
        this.siteName = siteName;
        this.charset = charset;
        this.url = url;
        this.fullUrl = fullUrl;
    }
}
