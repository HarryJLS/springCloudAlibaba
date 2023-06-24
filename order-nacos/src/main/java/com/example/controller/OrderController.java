package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JLS
 * @description:
 * @since 2023-06-03 21:27
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        System.out.println("下单成功");
        return "hello";
    }
}
