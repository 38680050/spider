package com.wsbxd.spider.domain.po;

import lombok.*;
import org.thymeleaf.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * description: 小说实体内容
 *
 * @author chenhaoxuan
 * @date 2018/9/2 17:03
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Content implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 上一页
     */
    private String prev;

    /**
     * 下一页
     */
    private String next;

    @Override
    public String toString() {
        return "ChapterDetail [title=" + title + ", content=" + StringUtils.abbreviate(content, 30) + ", prev=" + prev + ", next=" + next + "]";
    }

}
