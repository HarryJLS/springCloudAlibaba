package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author JLS
 * @description:
 * @since 2023-06-11 15:18
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
//        SpringApplication.run(Application.class, args);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        while(true) {
            //当动态配置刷新时，会更新到 Enviroment中，因此这里每隔一秒中从Enviroment中获取配置
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            System.err.println("user name :" + userName + "; age: " + userAge);
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
