package com.yangye.wechatrobot.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/12/2 14:04
 */
@Slf4j
public class ThreadUtil {


    public static void sleepSeconds(int seconds) {
        sleep(1000L * seconds);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
