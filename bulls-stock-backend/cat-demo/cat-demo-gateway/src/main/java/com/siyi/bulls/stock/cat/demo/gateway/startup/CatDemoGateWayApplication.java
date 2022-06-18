package com.siyi.bulls.stock.cat.demo.gateway.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-06 21:38
 * @Description: cat-demo网关服务启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.siyi")
public class CatDemoGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatDemoGateWayApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
