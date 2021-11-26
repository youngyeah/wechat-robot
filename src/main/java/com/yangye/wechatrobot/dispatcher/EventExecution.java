package com.yangye.wechatrobot.dispatcher;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/24 18:43
 */
@Data
public class EventExecution {
    private Method method;

    private Object obj;

}
