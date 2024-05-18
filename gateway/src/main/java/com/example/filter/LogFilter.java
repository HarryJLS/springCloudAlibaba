package com.example.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author JLS
 * @description:
 * @since 2023-06-23 16:52
 */
@Component
public class LogFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(LogFilter.class);

    /**
     * Process the Web request and (optionally) delegate to the next {@code WebFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("请求路径：{}", exchange.getRequest().getURI().getPath());

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 这个返回的值越小，优先值越高
        return 100;
    }
}
