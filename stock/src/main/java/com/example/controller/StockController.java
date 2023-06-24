package com.example.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author JLS
 * @description:
 * @since 2023-06-03 21:30
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        System.out.println("扣减库存成功");
        restTemplate.getForObject("http://localhost:8081/order/hello", String.class);
        return "hello";
    }
}
