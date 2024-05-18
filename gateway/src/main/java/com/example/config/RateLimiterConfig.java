package com.example.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author JLS
 * @description:
 * @since 2024-05-18 13:31
 */
@Configuration
public class RateLimiterConfig {

    @Bean
    KeyResolver keyResolver() {
        // 根据请求路径进行限流
//        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
        // 根据请求IP进行限流
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
        // 根据请求参数中的某个字段进行限流
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

}
