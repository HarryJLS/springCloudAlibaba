package com.example.controller;

//import com.example.feign.StockFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author JLS
 * @description:
 * @since 2023-06-03 21:27
 */
@RestController
@RequestMapping("/order")
public class OrderController {

//    @Resource
//    private StockFeignService stockFeignService;
//
//    @GetMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        System.out.println("下单成功");
//
//        stockFeignService.hello();
//        return "hello";
//    }
}
