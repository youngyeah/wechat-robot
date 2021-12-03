package com.yangye.wechatrobot.plugin.weather;

import com.yangye.wechatrobot.dispatcher.WxMessage;
import com.yangye.wechatrobot.plugin.AbstractPlugin;
import com.yangye.wechatrobot.reply.ReplyClient;
import com.yangye.wechatrobot.reply.ReplyEvent;
import com.yangye.wechatrobot.reply.message.GroupMessage;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/29 17:20
 */
public class WeatherForecastPlugin extends AbstractPlugin {

    @Override
    public boolean support(WxMessage fromMessage) {
        return true;
    }

    @Override
    public void reply(WxMessage fromMessage) {

    }
}
