package com.example.AOP;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.config.InitRateLimiter;
import com.example.config.RateLimiter;

@Aspect
@Component
public class RateLimitAspect {

    @Around("@annotation(rateLimit)")
    public Object aroundRateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String resourceName = rateLimit.resourceName();
        int initialCapacity = rateLimit.initialCapacity();
        int refillRate = rateLimit.refillRate();
        TimeUnit refillTimeUnit = rateLimit.refillTimeUnit();

        // 设置令牌桶
        InitRateLimiter.setRateLimiterMap(resourceName, new RateLimiter(initialCapacity, refillRate, refillTimeUnit));

        RateLimiter rateLimiter = InitRateLimiter.getRateLimiter(resourceName);

        if (!rateLimiter.allowRequest()) {
            throw new RuntimeException("限流了");
        }

        return joinPoint.proceed();
    }

}
