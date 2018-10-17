package com.wsbxd.spider.impl.book;

import com.wsbxd.common.constant.Constant;
import com.wsbxd.common.utils.DateUtil;
import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.spider.domain.po.Book;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * description: 笔下文学书籍爬取
 *
 * @author chenhaoxuan
 * @date 2018/10/2 21:41
 */
public class BxwxBookSpider extends AbstractBookSpider {

    @Override
    public List<Book> crawlBooks(String url, Integer maxTryNum) {
        List<Book> books = new ArrayList<>();
        try {
            Elements trs = super.crawlBooksPage(url, maxTryNum);
            String date = DateUtil.dateFormat(new Date(), DateUtil.DATE_TIME_PATTERN);
            for (int index = 0, size = trs.size(); index < size; index++) {
                Element tr = trs.get(index);
                System.out.println(tr);
                Elements tds = tr.getElementsByTag("div");
                Book book = new Book();
                book.setTitle(tds.first().getElementsByTag("a").first().attr("title"));
                String bookUrl = tds.get(1).getElementsByTag("a").first().absUrl("href");
                book.setUrl(bookUrl);
                book.setLastUpdateContent(tds.get(1).text());
                book.setLastUpdateContentUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
                book.setAuthor(tds.get(2).text());
                //yy-MM-dd
                book.setLastUpdateTime(tds.get(4).text());
                book.setAddTime(date);
                book.setUpdateTime(date);
                book.setStatus((String) redisUtil.getHashKey(Constant.REDIS_DICT_STATUS,tds.get(5).text()));
                book.setSiteId(NovelSiteEnum.getByUrl(url).getId());
                books.add(book);
            }
        } catch (Exception e) {
            throw new RuntimeException("url = " + url +" 书籍信息获取失败!");
        }
        return books;
    }
}
