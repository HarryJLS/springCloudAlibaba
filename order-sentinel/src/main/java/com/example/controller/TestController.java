package com.example.controller;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webSocketConfig.NoticeWebsocket;

/**
 * @author JLS
 * @description:
 * @since 2023-06-19 18:50
 */
@RestController
@RequestMapping("/test")
public class TestController {

    public static final Logger logger = Logger.getLogger("TestController");

    @RequestMapping("/testJls")
    @CrossOrigin
    public String test() throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            // 在这里编写异步任务的代码
            // 执行耗时操作或其他异步操作
            System.out.println("Async task started");

            // 模拟异步任务耗时操作
            try {
                Thread.sleep(2000);
                sendMessage();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // 异步任务完成后的处理
            System.out.println("Async task completed");
        });

        logger.info("测试成功");

        return "测试成功";
    }

    protected void sendMessage() throws InterruptedException, IOException {
        Thread.sleep(10000);
        NoticeWebsocket.sendMessage("hello world websocket");
    }
}
