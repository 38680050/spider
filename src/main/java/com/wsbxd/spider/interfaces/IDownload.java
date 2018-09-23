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

    String download(String url, DownloadConfig downloadConfig);

}
