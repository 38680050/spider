package com.wsbxd.spider.impl.download;

import com.wsbxd.common.utils.DownloadConfig;
import com.wsbxd.common.utils.FileUtil;
import com.wsbxd.common.utils.NovelSiteEnum;
import com.wsbxd.spider.domain.po.Chapter;
import com.wsbxd.spider.domain.po.Content;
import com.wsbxd.spider.factory.ChapterSpiderFactory;
import com.wsbxd.spider.factory.ContentSpiderFactory;
import com.wsbxd.spider.interfaces.IContentSpider;
import com.wsbxd.spider.interfaces.IDownload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

/**
 * description: 小说下载实现类
 *
 * @author chenhaoxuan
 * @date 2018/9/22 19:49
 */
public class DownloadImpl implements IDownload {

    @Override
    public String download(String url, DownloadConfig downloadConfig) {
        //根据章节列表爬虫工厂获取下载实现类,在用实现类获取章节列表
        List<Chapter> chapters = ChapterSpiderFactory.getChapterSpider(url).getChapters(url);
        //单个子线程下载完成后回复主线程,所有线程下载完成后合并
        int configSize = downloadConfig.getSize();
        int chaptersSize = chapters.size();
        //整数除法的截断而导致意想不到的结果,因此乘1.0,得出最大线程数
        int maxThreadSize = (int) Math.ceil(chaptersSize * 1.0 / configSize);
        //一般来说要给map初始化容量的,但小说章节实在不好确定,因此不定了
        Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<>();
        for (int i = 0; i < maxThreadSize; i++) {
            //当前开始下标
            int currentStartIndex = i * configSize;
            //当前下载下标
            int currentDownIndex = i == maxThreadSize - 1 ? chaptersSize : i * configSize + configSize;
            downloadTaskAlloc.put(currentStartIndex+"-"+currentDownIndex, chapters.subList(currentStartIndex,currentDownIndex));
        }
        //创建线程池,这样创建的线程池最大线程为MAX_INTEGER,以后得换方式创建
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreadSize);
        Set<String> keySet = downloadTaskAlloc.keySet();
        List<Future<String>> tasks = new ArrayList<>();
        //创建路径
        String savePath = downloadConfig.getPath() + "/" + NovelSiteEnum.getByUrl(url).getUrl();
        new File(savePath).mkdirs();

        for (String key : keySet) {
            tasks.add(executorService.submit(new DownloadCallable( downloadTaskAlloc.get(key),(savePath + "/" + key + ".txt"), downloadConfig.getMaxTryNum())));
        }
        executorService.shutdown();
        for (Future<String> future : tasks) {
            try {
                System.out.println(future.get() + ",下载完成！");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        FileUtil.multiFileMerge(savePath, null, true);
        return savePath + "/merge.txt";
    }

}

class DownloadCallable implements Callable<String> {

    /**
     * 章节列表
     */
    private List<Chapter> chapters;

    /**
     * 路径
     */
    private String path;

    /**
     * 最大尝试数
     */
    private int maxTryNum;

    @Override
    public String call() {
        try (PrintWriter out = new PrintWriter(new File(path),"UTF-8")) {
            for (Chapter chapter:chapters) {
                String url = chapter.getUrl();
                IContentSpider contentSpider = ContentSpiderFactory.getContentSpider(url);
                Content content = null;
                //循环下载
                for (int i = 0; i < maxTryNum; i++) {
                    try {
                        content = contentSpider.getContent(url);
                        out.println(content.getTitle());
                        out.println(content.getContent());
                        break;
                    }catch (RuntimeException e) {
                        System.err.println("尝试第[" + (i + 1) + "/" + maxTryNum + "]次下载失败了！");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    public DownloadCallable(List<Chapter> chapters, String path, int maxTryNum) {
        this.chapters = chapters;
        this.path = path;
        this.maxTryNum = maxTryNum;
    }

}
