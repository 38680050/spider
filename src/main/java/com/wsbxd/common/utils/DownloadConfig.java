package com.wsbxd.common.utils;

import lombok.*;

import java.io.Serializable;

/**
 * description: 下载配置类
 *
 * @author chenhaoxuan
 * @date 2018/9/22 19:53
 */
@ToString
public class DownloadConfig implements Serializable {

    /**
     * 单个线程默认下载最大章程数
     */
    private final static int DEFAULT_SIZE = 100;

    /**
     * 单个线程默认下载单章最大尝试次数
     */
    private final static int DEFAULT_TRY_NUM = 3;

    /**
     * 下载后文件保存位置
     */
    private String path;

    /**
     * 单个线程下载最大章程数
     */
    private int size;

    /**
     * 单个线程下载单章最大尝试次数
     */
    private int maxTryNum;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxTryNum() {
        return maxTryNum;
    }

    public DownloadConfig() {
        this.size = DEFAULT_SIZE;
        this.maxTryNum = DEFAULT_TRY_NUM;
    }

    public void setMaxTryNum(int maxTryNum) {
        this.maxTryNum = maxTryNum;
    }

    public DownloadConfig(String path) {
        this.path = path;
        this.size = DEFAULT_SIZE;
        this.maxTryNum = DEFAULT_TRY_NUM;
    }

    public DownloadConfig(String path, int maxTryNum) {
        this.path = path;
        this.size = DEFAULT_SIZE;
        this.maxTryNum = maxTryNum;
    }

    public DownloadConfig(String path, int size, int maxTryNum) {
        this.path = path;
        this.size = size;
        this.maxTryNum = maxTryNum;
    }
}
