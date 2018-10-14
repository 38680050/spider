package com.wsbxd.common.utils;

import org.springframework.stereotype.Component;

/**
 * description: 小说爬虫工具类
 *
 * @author chenhaoxuan
 * @date 2018/10/2 18:10
 */
@Component
public class NovelSpiderUtil {

    /**
     * 小说状态:连载
     */
    private final static String NOVEL_STATE_SERIAL = "连载";

    /**
     * 小说状态:完结
     */
    private final static String NOVEL_STATE_END = "完结";

    /**
     * 小说状态:完结
     */
    private final static String NOVEL_STATE_END2 = "完成";

    /**
     * 获取书籍的状态
     * @param status    小说状态
     * @return  小说状态码
     */
    public static int getBookStatus(String status) {
        if (status.contains(NOVEL_STATE_SERIAL)) {
            return 1;
        } else if (status.contains(NOVEL_STATE_END) || status.contains(NOVEL_STATE_END2)) {
            return 2;
        } else {
            throw new RuntimeException ("不支持的书籍状态：" + status);
        }
    }

}
