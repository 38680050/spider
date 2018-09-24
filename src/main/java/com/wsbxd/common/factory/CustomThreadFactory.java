package com.wsbxd.common.factory;

import java.util.concurrent.ThreadFactory;

/**
 * description: 自定义线程池工厂
 *
 * @author chenhaoxuan
 * @date 2018/9/24 11:22
 */
public class CustomThreadFactory implements ThreadFactory {

    /**
     * 线程池名称
     */
    private String name;

    /**
     * 线程计数
     */
    private Integer count;

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        return new Thread(thread);
    }

    public CustomThreadFactory(String name) {
        this.name = name;
        this.count = 0;
    }
}
