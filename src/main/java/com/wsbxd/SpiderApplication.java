package com.wsbxd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * description: 小说爬虫启动器
 *
 * @author chenhaoxuan
 * @date 2018/9/2 17:57
 */
@SpringBootApplication
@MapperScan("com.wsbxd.spider.mapper")
public class SpiderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class, args);
    }
}
