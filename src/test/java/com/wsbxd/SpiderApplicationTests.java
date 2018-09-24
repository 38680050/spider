package com.wsbxd;

import com.wsbxd.common.utils.DownloadConfig;
import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.impl.chapter.DefaultChapterSpider;
import com.wsbxd.spider.impl.download.DownloadImpl;
import com.wsbxd.spider.interfaces.IDownload;
import com.zaxxer.hikari.util.UtilityElf;
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

    @Test
    public void testDownload() {
        IDownload download = new DownloadImpl();
        DownloadConfig config = new DownloadConfig();
        config.setPath("D:/1");
        config.setSize(50);
        config.setMaxTryNum(3);
        System.out.println("下载好了，文件保存在：" + download.download("https://www.11kt.cn/read/72453/index.html", config) + "这里，赶紧去看看吧！");
    }

}