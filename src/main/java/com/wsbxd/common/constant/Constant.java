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
    String REDIS_NOVEL_SITE_CHARSET_SELECT = "spider:novel-site-charset-selector";

    /**
     * Redis中的所有小说章节列表选择器
     */
    String REDIS_NOVEL_LIST_SELECT = "spider:novel-list-selector";

    /**
     * Redis中的所有小说标题选择器
     */
    String REDIS_NOVEL_TITLE_SELECT = "spider:novel-title-selector";

    /**
     * Redis中的所有小说主体内容择器
     */
    String REDIS_NOVEL_CONTENT_SELECT = "spider:novel-content-selector";

    /**
     * Redis中的所有小说上一章选择器
     */
    String REDIS_NOVEL_PREV_SELECT = "spider:novel-prev-selector";

    /**
     * Redis中的所有小说下一章选择器
     */
    String REDIS_NOVEL_NEXT_SELECT = "spider:novel-next-selector";

    /**
     * Redis中的书籍选择器
     */
    String REDIS_NOVEL_BOOK_SELECT = "spider:novel-book-selector";

    /**
     * Redis中的下一页书籍列表选择器
     */
    String REDIS_NOVEL_NEXT_BOOK_SELECT = "spider:novel-nextBook-selector";

    /**
     * Redis中的书籍类型选择器
     */
    String REDIS_NOVEL_TYPE_SELECT = "spider:novel-type-selector";

    /**
     * Redis中的书籍状态
     */
    String REDIS_DICT_STATUS = "spider:novel-dict_status";

    /**
     * Redis中的书籍状态
     */
    String REDIS_DICT_TYPR = "spider:novel-dict_type";

    /**
     * 数字2
     */
    Integer NUMBER_TWO = 2;

    /**
     * 最大尝试次数
     */
    Integer MAX_TRY_NUM = 3;

}
