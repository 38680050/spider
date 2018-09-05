package com.wsbxd.common.domain;

import lombok.*;

/**
 * description: 小说站点XML解析
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
    private String chapterListSelector;
    /**
     * 小说标题选择器
     */
    private String contentTitleSelector;
    /**
     * 小说内容选择器
     */
    private String contentContentSelector;
    /**
     * 上一章选择器
     */
    private String contentPrevSelector;
    /**
     * 下一章选择器
     */
    private String contentNextSelector;
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

    public String getChapterListSelector(Integer index) {
        String[] split = chapterListSelector.split("\\|");
        return getIndex(index, split);
    }

    public String getContentTitleSelector(Integer index) {
        String[] split = contentTitleSelector.split("\\|");
        return getIndex(index, split);
    }

    public String getContentContentSelector(Integer index) {
        String[] split = contentContentSelector.split("\\|");
        return getIndex(index, split);
    }

    public String getContentPrevSelector(Integer index) {
        String[] split = contentPrevSelector.split("\\|");
        return getIndex(index, split);
    }

    public String getContentNextSelector(Integer index) {
        String[] split = contentNextSelector.split("\\|");
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
