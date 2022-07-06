package com.siyi.nacos.config.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-06 00:20
 * @Description:
 */
@SpringBootApplication
public class NacosConfigDemoApplication {

    @Value("${stockName:中国平安}")
    private String stockName;

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigDemoApplication.class, args);
    }
}
