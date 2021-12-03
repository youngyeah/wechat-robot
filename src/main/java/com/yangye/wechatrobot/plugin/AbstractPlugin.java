package com.yangye.wechatrobot.plugin;

import com.yangye.wechatrobot.dispatcher.WxMessage;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/26 11:31
 */
public abstract class AbstractPlugin implements Plugin {

    /**
     * 子类实现
     * @return
     */
    @Override
    public boolean support(WxMessage fromMessage) {
        throw new UnsupportedOperationException();
    }


    /**
     * 子类实现
     */
    @Override
    public void apply(WxMessage fromMessage) {
        if (support(fromMessage)) {
            reply(fromMessage);
        }
    }

    @Override
    public void reply(WxMessage fromMessage) {
        throw new UnsupportedOperationException();
    }
}
