package com.example.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author JLS
 * @description: 自定义feign拦截器
 * @since 2023-06-10 21:39
 */
//@Configuration
public class CustomFeignInterceptor implements RequestInterceptor {

    Logger logger = LoggerFactory.getLogger(CustomFeignInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("token", "123456");
//        requestTemplate.query("name", "zhangsan");
//        requestTemplate.uri("/6");

        logger.info("feign interceptor");

    }
}
