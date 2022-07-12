package com.siyi.trade.grpc.client.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-12 16:19
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.siyi"})
public class GrpcClientStartup {

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientStartup.class, args);
    }
}