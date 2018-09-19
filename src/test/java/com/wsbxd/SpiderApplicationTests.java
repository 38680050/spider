package com.wsbxd;

import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.factory.ChapterSpiderFactory;
import com.wsbxd.spider.interfaces.IChapterSpider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider("https://www.11kt.cn/read/72453/index.html");
        List<Chapter> chapters = chapterSpider.getChapters("https://www.11kt.cn/read/72453/index.html");
        for (Chapter chapter:chapters){
            System.out.println(chapter);
        }
    }

}
