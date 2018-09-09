package com.wsbxd.common.domain;

import lombok.*;

/**
 * description: 小说站点选择器XML解析
 *
 * @author chenhaoxuan
 * @date 2018/9/2 17:57
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String chapterList;
    /**
     * 小说标题选择器
     */
    private String contentTitle;
    /**
     * 小说内容选择器
     */
    private String contentContent;
    /**
     * 上一章选择器
     */
    private String contentPrev;
    /**
     * 下一章选择器
     */
    private String contentNext;
    /**
     * 最大数组长度
     */
    private final static Integer ARRAY_LENGHT = 2;

    public String getTitle() {
        return title;
    }

    public String getCharset() {
        return charset;
    }

    public String getUrl() {
        return url;
    }

    public String getChapterList(Integer index) {
        String[] split = chapterList.split("\\|");
        return getIndex(index, split);
    }

    public String getContentTitle(Integer index) {
        String[] split = contentTitle.split("\\|");
        return getIndex(index, split);
    }

    public String getContentContent(Integer index) {
        String[] split = contentContent.split("\\|");
        return getIndex(index, split);
    }

    public String getContentPrev(Integer index) {
        String[] split = contentPrev.split("\\|");
        return getIndex(index, split);
    }

    public String getContentNext(Integer index) {
        String[] split = contentNext.split("\\|");
        return getIndex(index, split);
    }

    /**
     * 根据下标获取值
     * @param index 下标
     * @param split 字符串数组
     * @return
     */
    private String getIndex(Integer index, String[] split) {
        if (index == 0) {
            return split[0];
        } else if (index == 1) {
            if (split.length == ARRAY_LENGHT) {
                return split[1];
            } else {
                return "0";
            }
        } else {
            throw new RuntimeException("小说站点没有此下标!");
        }
    }
}
