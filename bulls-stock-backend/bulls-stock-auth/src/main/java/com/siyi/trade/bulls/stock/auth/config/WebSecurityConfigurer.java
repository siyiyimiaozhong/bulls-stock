package com.siyi.trade.bulls.stock.auth.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: siyiyimiaozhong
 * @Description:
 * @Project: bulls-stock-backend
 * @Package: com.siyi.bulls.stock.trade.auth.config
 * @ClassName: WebSecurityConfigurer.java
 * @CreateTime: 2022-11-17  12:41
 * @Version: 1.0
 */
@Primary
@Order(90)
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    /**
     * oauth用户信息认证实现类
     */
    private final UserDetailsService authStockUserDetailService;

    /**
     * 用户密码的编码处理器
     */
    private final StockPasswordEncoder stockPasswordEncoder;

    public WebSecurityConfigurer(UserDetailsService authStockUserDetailService, StockPasswordEncoder stockPasswordEncoder) {
        this.authStockUserDetailService = authStockUserDetailService;
        this.stockPasswordEncoder = stockPasswordEncoder;
    }

    /**
     * Web服务认证配置
     *
     * @param http
     */
    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {

        http.formLogin()
                .loginPage("/token/login")
                .loginProcessingUrl("/token/form")
                .defaultSuccessUrl("/token/success")
                .and()
                .authorizeRequests()
                .antMatchers("/token/**", "/actuator/**", "/druid/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }


    /**
     * 不拦截静态资源
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**");
    }

    /**
     * 认证管理器
     *
     * @return
     */
    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }


    /**
     * 将自定义的用户鉴权类和密码编码器加入到认证服务中
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authStockUserDetailService).passwordEncoder(stockPasswordEncoder);
    }
}
