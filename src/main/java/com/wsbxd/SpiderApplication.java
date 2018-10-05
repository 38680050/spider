package com.wsbxd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * description: 小说爬虫启动器
 * EnableScheduling 定时任务注解
 *
 * @author chenhaoxuan
 * @date 2018/9/2 17:57
 */
@EnableScheduling
@SpringBootApplication
@MapperScan("com.wsbxd.spider.mapper")
public class SpiderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class, args);
    }

}
