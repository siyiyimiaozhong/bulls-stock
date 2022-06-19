package com.siyi.hateoas.demo.stocks.startup;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-19 15:08
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.siyi"})
@EntityScan(basePackages = {"com.siyi"})
@EnableJpaRepositories(basePackages = {"com.siyi"})
public class HateoasStocksApplication {
    public static void main(String[] args) {
        SpringApplication.run(HateoasStocksApplication.class, args);
    }

    /**
     * Hibernate初始化
     * @return
     */
    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    /**
     * 用户json数据封装处理
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.indentOutput(true);
        };
    }
}
