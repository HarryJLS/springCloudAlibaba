package com.example.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JLS
 * @description:
 * @since 2023-06-17 17:57
 */

//@Component
//public class MyBlockExceptionHandler implements BlockExceptionHandler {
//
//    public static final Logger logger = LoggerFactory.getLogger(MyBlockExceptionHandler.class);
//
//    @Override
//    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
//        logger.info("BlockExceptionHandler BlockException:{}", e);
//
//        if (e instanceof FlowException) {
//            httpServletResponse.setStatus(429);
//            httpServletResponse.getWriter().print("请求过于频繁");
//        } else {
//            httpServletResponse.setStatus(500);
//            httpServletResponse.getWriter().print("系统异常");
//        }
//    }
//}
