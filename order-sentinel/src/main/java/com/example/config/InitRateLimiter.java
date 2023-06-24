package com.example.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JLS
 * @description:
 * @since 2023-06-15 21:55
 */
@Component
public class InitRateLimiter {

    public static Map<String, RateLimiter> rateLimiterMap = new HashMap<>();

    public static void setRateLimiterMap(String resourceName, RateLimiter rateLimiter) {
        rateLimiterMap.putIfAbsent(resourceName, rateLimiter);
    }

    public static RateLimiter getRateLimiter(String resourceName) {
        return rateLimiterMap.get(resourceName);
    }
}
