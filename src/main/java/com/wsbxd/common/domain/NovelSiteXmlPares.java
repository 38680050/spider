package com.wsbxd.common.domain;

import lombok.*;

/**
 * description: 小说站点XML解析
 *
 * @author chenhaoxuan
 * @date 2018/9/2 17:57
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NovelSiteXmlPares {

    /**
     * 站点名
     */
    private String title;
    /**
     * 字符编码
     */
    private String charset;
    /**
     * 链接
     */
    private String url;
    /**
     * 章节列表选择器
     */
    private Selector chapterListSelector;
    /**
     * 小说标题选择器
     */
    private Selector contentTitleSelector;
    /**
     * 小说内容选择器
     */
    private Selector contentContentSelector;
    /**
     * 上一章选择器
     */
    private Selector contentPrevSelector;
    /**
     * 下一章选择器
     */
    private Selector contentNextSelector;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Selector {
        /**
         * 选择器
         */
        private String select;
        /**
         * 下标
         */
        private String index;
    }

}
