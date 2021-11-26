package com.yangye.wechatrobot.controller;

import com.yangye.wechatrobot.dispatcher.EventHandlerDispatcher;
import com.yangye.wechatrobot.dispatcher.WxMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/23 13:36
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class RobotApiController {

    @Autowired
    private EventHandlerDispatcher eventHandlerDispatcher;

    @PostMapping("/wechat")
    public void wechat(@RequestBody WxMessage wxMessage) {
        eventHandlerDispatcher.dispatch(wxMessage);
    }
}
