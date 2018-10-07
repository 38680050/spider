package com.wsbxd.spider.domain.po;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * description: 小说选择器实体类
 *
 * @author chenhaoxuan
 * @date 2018/9/2 17:57
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "t_novel_selector")
public class NovelSelector {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 章节列表选择器
     */
    @Column
    private String url;
    /**
     * 章节列表选择器
     */
    @Column
    private String list;
    /**
     * 小说标题选择器
     */
    @Column
    private String title;
    /**
     * 小说内容选择器
     */
    @Column
    private String content;
    /**
     * 上一章选择器
     */
    @Column
    private String prev;
    /**
     * 下一章选择器
     */
    @Column
    private String next;
    /**
     * 书籍选择器
     */
    @Column
    private String book;
    /**
     * 下一本书籍选择器
     */
    @Column(name = "nextPage")
    private String nextPage;
    /**
     * 书籍类型选择器
     */
    @Column
    private String type;
    /**
     * 最大数组长度
     */
    @Transient
    private final static Integer ARRAY_LENGHT = 2;

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPrev() {
        return prev;
    }

    public String getNext() {
        return next;
    }

    public String getPage() {
        return book;
    }

    public String getNextPage() {
        return nextPage;
    }

    public String getBook() {
        return book;
    }

    public String getType() {
        return type;
    }

    public String getList(Integer index) {
        String[] split = list.split("\\|");
        return getSelectorByIndex(index, split);
    }

    public String getTitle(Integer index) {
        String[] split = title.split("\\|");
        return getSelectorByIndex(index, split);
    }

    public String getContent(Integer index) {
        String[] split = content.split("\\|");
        return getSelectorByIndex(index, split);
    }

    public String getPrev(Integer index) {
        String[] split = prev.split("\\|");
        return getSelectorByIndex(index, split);
    }

    public String getNext(Integer index) {
        String[] split = next.split("\\|");
        return getSelectorByIndex(index, split);
    }

    public String getBook(Integer index) {
        String[] split = book.split("\\|");
        return getSelectorByIndex(index, split);
    }

    public String getNextPage(Integer index) {
        String[] split = nextPage.split("\\|");
        return getSelectorByIndex(index, split);
    }

    public String getType(Integer index) {
        String[] split = type.split("\\|");
        return getSelectorByIndex(index, split);
    }

    /**
     * 根据下标获取值
     * @param index 下标
     * @param split 字符串数组
     * @return
     */
    private String getSelectorByIndex(Integer index, String[] split) {
        if (index == 0) {
            return split[0];
        } else if (index == 1) {
            if (split.length == ARRAY_LENGHT) {
                return split[1];
            } else {
                return "0";
            }
        } else {
            throw new RuntimeException("章节列表没有此下标!");
        }
    }
}
