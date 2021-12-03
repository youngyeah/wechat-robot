package com.yangye.wechatrobot.plugin;

import com.yangye.wechatrobot.dispatcher.WxMessage;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/26 11:28
 */
public interface Plugin {

    boolean support(WxMessage fromMessage);

    void apply(WxMessage fromMessage);

    void reply(WxMessage fromMessage);
}
