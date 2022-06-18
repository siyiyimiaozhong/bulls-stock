package com.siyi.bulls.stock.cat.demo.account.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-07 21:52
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.siyi"})
public class DemoAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAccountApplication.class, args);
    }
}
