package com.wsbxd;

import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.common.utils.NovelSiteUtil;
import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.service.IChapterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {

    @Autowired
    private IChapterService chapterService;

    @Test
    public void testGetChapters() {
        List<Chapter> chapters = chapterService.getChapter("https://www.11kt.cn/read/72453/index.html");
        for (Chapter chapter:chapters) {
            System.out.println(chapter);
        }
    }

    @Test
    public void testGetSiteContext() {
        System.out.println(NovelSiteUtil.getParserRule(NovelSiteEnum.getByUrl("https://www.xs.la/0_5/")));
    }

}
