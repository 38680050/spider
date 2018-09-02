package com.wsbxd.spider.service;

import com.wsbxd.spider.domain.po.Content;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/2 17:06
 */
public interface IContextService {

    /**
     * 根据链接获取小说内容
     * @param url 链接
     * @return 小说内容
     */
    Content getContext(String url);

}
