package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.AOP.RateLimit;
import com.example.config.RateLimiter;
import com.example.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author JLS
 * @description:
 * @since 2023-06-03 21:27
 */
@RestController
@RequestMapping("/order")
public class OrderController {
//    RateLimiter rateLimiter = new RateLimiter(20, 1, TimeUnit.MINUTES);

    @Resource
    private TestService testService;

    @GetMapping("/hello")
    @ResponseBody
    @RateLimit(resourceName = "hello", initialCapacity = 10, refillRate = 1, refillTimeUnit = TimeUnit.SECONDS)
    public String hello() {


            System.out.println("下单成功");
            return "hello";


    }

    @GetMapping("/hello1")
    @ResponseBody
    @SentinelResource(value = "hello1", blockHandler = "blockHandler")
    public String hello1() {

        System.out.println("下单成功");
        return "hello";

    }

    @GetMapping("/hello2")
    @ResponseBody
    @SentinelResource(value = "hello2", blockHandler = "blockHandler")
    public String hello2() throws InterruptedException {

        Thread.sleep(2000);
        System.out.println("下单成功");
        return "hello2";

    }

    private String blockHandler(BlockException blockException) {
        return "当前服务繁忙，请稍后再试";
    }

    @GetMapping("/get")
    @ResponseBody
    public String get() {
        System.out.println("查询订单");
        return "查询订单";
    }

    @GetMapping("/set")
    @ResponseBody
    public String set() {
        System.out.println("修改订单");
        return "修改订单";
    }

    @GetMapping("/test1")
    @ResponseBody
    public String test1() {
        return testService.test();
    }

    @GetMapping("/test2")
    @ResponseBody
    public String test2() {
        return testService.test();
    }

    @GetMapping("/flowThread")
    @ResponseBody
    public String flowThread() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("正常访问");
        return "正常访问";
    }


    @GetMapping("/get/{id}")
    @SentinelResource(value = "get", blockHandler = "blockHandlerNew",
            fallback = "fallback", fallbackClass = TestService.class)
    public String get(@PathVariable("id") Integer id) {
        System.out.println("正常访问");
        return "正常访问";
    }
    private String blockHandlerNew(BlockException blockException) {
        return "热点数据处理";
    }

    public String fallback(Integer id, Throwable e) {
        return "服务降级";
    }

}
