package com.wsbxd.spider.interfaces;

import com.wsbxd.common.utils.DownloadConfig;

/**
 * description: 小说下载接口
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/19 16:08
 */
public interface IDownload {

    /**
     * 整本小说下载
     * @param url               链接
     * @param downloadConfig    下载配置
     * @return 路径书名
     */
    String download(String url, DownloadConfig downloadConfig);

}
