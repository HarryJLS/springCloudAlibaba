package com.example.config;

import feign.Contract;
import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JLS
 * @description: 全局配置：加上configuration注解，会将配置做用到左右的服务的提供方 如果针对某一个服务进行配置，则不用加@Configuration注解
 * @since 2023-06-10 21:02
 */
// @Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 契约配置，支持feign原生注解
     */
    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }


    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }
}
