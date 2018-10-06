package com.wsbxd.spider.impl.book;

import com.wsbxd.common.utils.DateUtil;
import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.common.utils.NovelSpiderUtil;
import com.wsbxd.spider.domain.po.Book;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * description: 看书中书籍爬虫
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/20 14:41
 */
public class KanShuZhongBookSpider extends AbstractBookSpider {

    /**
     * 获取看书中书籍列表
     * @param url           链接
     * @param maxTryNum     最大尝试次数
     * @return  书籍列表
     */
    @Override
    public List<Book> crawlBooks(String url, Integer maxTryNum) {
        List<Book> books = new ArrayList<>();
        try {
            Elements trs = super.crawlBooksPage(url, maxTryNum);
            //入库时间
            String date = DateUtil.dateFormat(new Date(), DateUtil.DATE_TIME_PATTERN);
            for (int index = 1; index < (trs.size() - 1); index++) {
                Elements tds = trs.get(index).getElementsByTag("td");
                Book book = new Book();
                book.setType(tds.first().text());
                book.setTitle(tds.get(1).text());
                book.setUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
                book.setLastUpdateContent(tds.get(2).text());
                book.setLastUpdateContentUrl(tds.get(2).getElementsByTag("a").first().absUrl("href"));
                book.setAuthor(tds.get(3).text());
                book.setLastUpdateTime(tds.get(4).text());
                book.setAddTime(date);
                book.setUpdateTime(date);
                book.setStatus(NovelSpiderUtil.getBookStatus(tds.get(5).text()));
                book.setSiteId(NovelSiteEnum.getByUrl(url).getId());
                books.add(book);
            }
            bookMapper.insertList(books);
        } catch (Exception e) {
            throw new RuntimeException("url = " + url +" 书籍信息获取失败!");
        }
        return books;
    }

}
