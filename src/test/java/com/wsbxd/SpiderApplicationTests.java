package com.wsbxd;

import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.impl.chapter.DefaultChapterSpider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {

    @Test
    public void testGetContext() {

    }

    @Test
    public void testGetChapters() {
        DefaultChapterSpider spider = new DefaultChapterSpider();
        List<Chapter> chapters = spider.getChapters("https://www.11kt.cn/read/72453/index.html");
        for (Chapter chapter:chapters){
            System.out.println(chapter);
        }
    }

}