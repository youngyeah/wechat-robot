package com.yangye.wechatrobot.handler;

import com.alibaba.fastjson.JSON;
import com.yangye.wechatrobot.WxEvent;
import com.yangye.wechatrobot.dispatcher.EventHandler;
import com.yangye.wechatrobot.dispatcher.EventMapping;
import com.yangye.wechatrobot.dispatcher.WxMessage;
import com.yangye.wechatrobot.plugin.chat.ChatPlugin;
import com.yangye.wechatrobot.plugin.horserace.HorseRacePlugin;
import com.yangye.wechatrobot.plugin.tiangou.TianGouPlugin;
import com.yangye.wechatrobot.plugin.tigang.TiGangPlugin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    private TianGouPlugin tianGouPlugin;
    @Autowired
    private TiGangPlugin tiGangPlugin;
    @Autowired
    private ChatPlugin chatPlugin;

    @EventMapping(event = WxEvent.EventGroupMsg)
    public void handleGroupMsg(WxMessage message) {
        log.info(JSON.toJSONString(message));
        if (horseRacePlugin.support(message)) {
            horseRacePlugin.apply(message);
        } else if (tianGouPlugin.support(message)) {
            tianGouPlugin.apply(message);
        } else if (tiGangPlugin.support(message)) {
            tiGangPlugin.apply(message);
        } else {
            chatPlugin.apply(message);
        }
    }

    @EventMapping(event = WxEvent.EventFriendMsg)
    public void handleFriendMsg(WxMessage message) {
        log.info(message.toString());
    }

    @EventMapping(event = WxEvent.EventSendOutMsg)
    public void handleSendOutMsg(WxMessage message) {
        log.info(message.toString());
    }
}
