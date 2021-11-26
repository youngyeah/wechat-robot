package com.yangye.wechatrobot.handler;

import com.alibaba.fastjson.JSON;
import com.yangye.wechatrobot.WxEvent;
import com.yangye.wechatrobot.dispatcher.EventHandler;
import com.yangye.wechatrobot.dispatcher.EventMapping;
import com.yangye.wechatrobot.dispatcher.WxMessage;
import com.yangye.wechatrobot.plugin.PluginCmd;
import com.yangye.wechatrobot.plugin.horserace.HorseRacePlugin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/24 19:43
 */
@EventHandler
@Slf4j
public class MsgEventHandler {

    @Autowired
    private HorseRacePlugin horseRacePlugin;

    @EventMapping(event = WxEvent.EventGroupMsg)
    public void handleGroupMsg(WxMessage message) {
        log.info(JSON.toJSONString(message));
        if (Objects.equals("8436450194@chatroom", message.getFromWxid()) && Objects.equals(message.getMsg(), "赛马游戏")) {
            horseRacePlugin.apply(PluginCmd.HORSE_RACE, message);
        }
    }

    @EventMapping(event = WxEvent.EventFriendMsg)
    public void handleFriendMsg(WxMessage message) {
        log.info(message.toString());
    }
}
