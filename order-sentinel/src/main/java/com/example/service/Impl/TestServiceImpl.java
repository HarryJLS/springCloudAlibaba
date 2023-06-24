package com.example.service.Impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author JLS
 * @description:
 * @since 2023-06-17 16:41
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    @SentinelResource(value = "test", blockHandler = "blockHandler")
    public String test() {
        return "测试成功";
    }

    public String blockHandler(BlockException e) {
        return "限流了";
    }
}
