package com.wsbxd.spider.impl.book;

import com.wsbxd.common.constant.Constant;
import com.wsbxd.common.utils.BeanHelper;
import com.wsbxd.common.utils.RedisSelectorEnum;
import com.wsbxd.spider.domain.po.Book;
import com.wsbxd.spider.impl.AbstractSpider;
import com.wsbxd.spider.interfaces.IBookSpider;
import com.wsbxd.spider.mapper.BookMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.List;

/**
 * description: 小说书籍爬虫抽象类
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/20 14:39
 */
public abstract class AbstractBookSpider extends AbstractSpider implements IBookSpider {

    protected static BookMapper bookMapper;

    static {
        bookMapper = BeanHelper.getBean(BookMapper.class);
    }

    /**
     * 下一页的元素
     */
    protected Element nextPageElement;

    /**
     * 下一页的url
     */
    protected String nextPage;

    /**
     * 默认抓取方法,最多尝试 {@link Constant#MAX_TRY_NUM} 次获取书籍调用
     * @param url   链接
     * @return  书籍元素
     */
    protected Elements crawlBooksPage(String url){
        return crawlBooksPage(url, Constant.MAX_TRY_NUM);
    }

    /**
     * 抓取方法,最多尝试 maxTryNum 次
     * @param url           链接
     * @param maxTryNum     最大尝试次数
     * @return  书籍元素
     */
    protected Elements crawlBooksPage(String url, Integer maxTryNum){
        maxTryNum = maxTryNum.equals(null) ? Constant.MAX_TRY_NUM : maxTryNum;
        Elements bookElements = null;
        for (int i = 0 ; i < maxTryNum ; i++ ){
            String result = super.crawl(url);
            Document document = Jsoup.parse(result);
            //添加url,以便后面链接的连接都自动加上域名
            document.setBaseUri(url);
            //获取书籍标签,再根据书籍标签获取元素
            bookElements = document.select(getSelectorByIndex(url, RedisSelectorEnum.BOOK, 0));
            Elements nextBookElements = document.select(getSelectorByIndex(url, RedisSelectorEnum.NEXT_BOOK, 0));
            //如果获取元素不为空,则获取第一个
            nextPageElement = nextBookElements == null ? null : nextBookElements.first();
            if (nextPageElement != null) {
                nextPage = nextPageElement.absUrl("href");
            } else {
                nextPage = "";
            }
        }
        if (bookElements == null){
            throw new RuntimeException(url + ",尝试了" + maxTryNum + "次依然获取书籍失败了！");
        }
        return bookElements;
    }

    @Override
    public boolean hasNext() {
        return StringUtils.isNotEmpty(nextPage);
    }

    @Override
    public String next() {
        return nextPage;
    }

    @Override
    public Iterable<List<Book>> iterator(String firstPage, Integer maxTryNum) {
        return null;
    }

    /**
     * 书籍迭代器
     */
    @AllArgsConstructor
    private class BookIterator implements Iterator<List<Book>> {

        /**
         * 最大尝试次数
         */
        private Integer maxTryNum;

        @Override
        public boolean hasNext() {
            return AbstractBookSpider.this.hasNext();
        }

        @Override
        public List<Book> next() {
            return null;
        }
    }

}
