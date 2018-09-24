package com.wsbxd.common.constant;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/2 10:25
 */
public interface Constant {

    /**
     * Redis中的所有小说站点选择器
     */
    String REDIS_NOVEL_SITE_CHARSET = "spider:novel-site-charset-all";

    /**
     * Redis中的所有小说章节列表选择器
     */
    String REDIS_NOVEL_LIST = "spider:novel-list-all";

    /**
     * Redis中的所有小说标题选择器
     */
    String REDIS_NOVEL_TITLE = "spider:novel-title-all";

    /**
     * Redis中的所有小说主体内容择器
     */
    String REDIS_NOVEL_CONTENT = "spider:novel-content-all";

    /**
     * Redis中的所有小说上一章选择器
     */
    String REDIS_NOVEL_PREV = "spider:novel-prev-all";

    /**
     * Redis中的所有小说下一章选择器
     */
    String REDIS_NOVEL_NEXT = "spider:novel-next-all";

    /**
     * Redis中的图书选择器
     */
    String REDIS_NOVEL_BOOK = "spider:novel-book-all";

    /**
     * Redis中的下一本图书选择器
     */
    String REDIS_NOVEL_NEXT_BOOK = "spider:novel-nextBook-all";

    /**
     * 数字2
     */
    Integer NUMBER_TWO = 2;

    /**
     * 最大尝试次数
     */
    Integer MAX_TRY_NUM = 3;

}
