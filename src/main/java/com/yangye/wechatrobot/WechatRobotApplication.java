package com.yangye.wechatrobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WechatRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatRobotApplication.class, args);
    }

}
