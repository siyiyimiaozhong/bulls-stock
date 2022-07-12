package com.siyi.trade.grpc.server.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-12 19:56
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.siyi"})
public class GrpcServerStartup {
    public static void main(String[] args) {
        SpringApplication.run(GrpcServerStartup.class, args);
    }
}
