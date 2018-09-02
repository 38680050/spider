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
@Table(name = "t_context")
public class Content implements Serializable {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    @Column
    private String title;

    /**
     * 内容
     */
    @Column
    private String content;

    /**
     * 上一页
     */
    @Column
    private String prev;

    /**
     * 下一页
     */
    @Column
    private String next;

    @Override
    public String toString() {
        return "ChapterDetail [title=" + title + ", content=" + StringUtils.abbreviate(content, 30) + ", prev=" + prev + ", next=" + next + "]";
    }

}
