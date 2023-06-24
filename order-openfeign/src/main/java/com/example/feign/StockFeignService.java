package com.example.feign;

import com.example.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JLS
 * @description:
 * @since 2023-06-10 18:13
 */
//name指定rest接口所在的服务名， path指定服务的前缀
@FeignClient(name="stock-service", path = "/stock")
public interface StockFeignService {

    // 声明需要调用的rest接口
    @GetMapping("/hello")
    @ResponseBody
    public String hello();
}
