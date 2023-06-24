package com.example.controller;

import com.example.AOP.RateLimit;
import com.example.config.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author JLS
 * @description:
 * @since 2023-06-15 21:26
 */
@RestController
@RequestMapping("/orderNew")
public class OrdernEWController {

    // RateLimiter rateLimiter = new RateLimiter(20, 1, TimeUnit.MINUTES);

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {

        System.out.println("下单成功");
        return "hello";

    }

    @GetMapping("/hello1")
    @ResponseBody
    @RateLimit(resourceName = "myMethod", initialCapacity = 10, refillRate = 1, refillTimeUnit = TimeUnit.MINUTES)
    public String hello1() {
        System.out.println("下单成功New");
        return "hello";

    }
}
