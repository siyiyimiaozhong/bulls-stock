package com.siyi.nacos.discovery.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-06 07:32
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosDiscoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryServerApplication.class, args);
    }
}
