package com.wsbxd.common.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * description: 定时任务配置
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2018/10/5 10:14
 */
@Component
public class ScheduledTasks {

    /**
     * 定时任务,每天中午12点触发一次
     */
    @Scheduled(cron = "“0 0 12 * * ?”")
    public void test(){
        System.out.println("123");
    }

}
