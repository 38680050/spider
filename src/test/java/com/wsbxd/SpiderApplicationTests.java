package com.wsbxd;

import com.wsbxd.common.utils.DownloadConfig;
import com.wsbxd.spider.domain.po.*;
import com.wsbxd.spider.factory.BookSpiderFactory;
import com.wsbxd.spider.factory.SiteSpiderFactory;
import com.wsbxd.spider.impl.chapter.DefaultChapterSpider;
import com.wsbxd.spider.impl.content.DefaultContentSpider;
import com.wsbxd.spider.impl.download.DownloadImpl;
import com.wsbxd.spider.interfaces.IBookSpider;
import com.wsbxd.spider.interfaces.IDownload;
import com.wsbxd.spider.interfaces.ISiteSpider;
import com.wsbxd.spider.service.IBookService;
import com.wsbxd.spider.service.IChapterService;
import com.wsbxd.spider.service.IContentService;
import com.wsbxd.spider.service.ISiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ISiteService siteService;

    @Autowired
    private IChapterService chapterService;

    @Autowired
    private IContentService contentService;

    @Test
    public void test() throws Exception {

    }

    /**
     * 遍历添加小说类型
     */
    @Test
    public void insertTypes() {
        List<Site> sites = siteService.findAll();
        for (Site site : sites) {
            boolean flag = siteService.insertTypes(site.getFullUrl());
            if (!flag) {
                System.out.println(site.getFullUrl()+"失败!");
            }
        }
    }

    @Test
    public void testInsertBooks() {
        boolean flag = bookService.insertBooks("http://www.kanshuzhong.com/map/A/1/", 3);
        System.out.println(flag);
    }

    @Test
    public void testKszIterator() {
        IBookSpider spider = BookSpiderFactory.getBookSpider("http://www.kanshuzhong.com/map/A/1/");
        Iterator<List<Book>> iterator = spider.iterator("http://www.kanshuzhong.com/map/A/1/", 3);
        while (iterator.hasNext()) {
            List<Book> books = iterator.next();
			for (Book book : books) {
				System.out.println(book);
			}
        }
    }

    @Test
    public void testGetTypes() {
        ISiteSpider spider = SiteSpiderFactory.getSiteService("http://www.kanshuzhong.com/");
        List<Type> types = spider.crawlTypes("http://www.kanshuzhong.com/");
        for (Type type:types) {
            System.out.println(type);
        }
    }

    @Test
    public void testGetBooks() {
        IBookSpider spider = BookSpiderFactory.getBookSpider("http://www.kanshuzhong.com/map/A/1/");
        List<Book> Books = spider.crawlBooks("http://www.kanshuzhong.com/map/A/1/", 10);
        for (Book Book : Books) {
            System.out.println(Book);
        }
    }

    @Test
    public void testGetContext() {
        DefaultContentSpider spider = new DefaultContentSpider();
        Content content = spider.crawlContent("http://www.kanshuzhong.com/book/23729/5669551.html");
        System.out.println(content);
    }

    @Test
    public void testGetChapters() {
        DefaultChapterSpider spider = new DefaultChapterSpider();
        List<Chapter> chapters = spider.crawlChapters("http://www.kanshuzhong.com/book/23729/");
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
        System.out.println("下载好了，文件保存在：" + download.download("http://www.kanshuzhong.com/book/23729/", config) + "这里，赶紧去看看吧！");
    }

}