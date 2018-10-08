package com.wsbxd;

import com.wsbxd.common.utils.DownloadConfig;
import com.wsbxd.spider.domain.po.Book;
import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.domain.po.Content;
import com.wsbxd.spider.domain.po.Type;
import com.wsbxd.spider.factory.BookSpiderFactory;
import com.wsbxd.spider.factory.SiteSpiderFactory;
import com.wsbxd.spider.impl.chapter.DefaultChapterSpider;
import com.wsbxd.spider.impl.content.DefaultContentSpider;
import com.wsbxd.spider.impl.download.DownloadImpl;
import com.wsbxd.spider.interfaces.IBookSpider;
import com.wsbxd.spider.interfaces.IDownload;
import com.wsbxd.spider.interfaces.ISiteSpider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {

    @Test
    public void test() throws Exception {

    }

    @Test
    public void testKszIterator() {
        IBookSpider spider = BookSpiderFactory.getBookSpider("http://www.kanshuzhong.com/map/A/1/");
        Iterator<List<Book>> iterator = spider.iterator("http://www.kanshuzhong.com/map/A/1/", 3);
        while (iterator.hasNext()) {
            List<Book> books = iterator.next();
            System.err.println("URL：" + spider.next());
			for (Book book : books) {
				System.out.println(book);
			}
        }
    }

    @Test
    public void testGetTypes() {
        ISiteSpider spider = SiteSpiderFactory.getSiteService("https://www.bxwx9.org");
        List<Type> types = spider.crawlTypes("https://www.bxwx9.org");
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
        Content content = spider.getContent("http://www.kanshuzhong.com/book/23729/5669551.html");
        System.out.println(content);
    }

    @Test
    public void testGetChapters() {
        DefaultChapterSpider spider = new DefaultChapterSpider();
        List<Chapter> chapters = spider.getChapters("http://www.kanshuzhong.com/book/23729/");
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