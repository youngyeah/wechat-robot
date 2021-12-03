package com.yangye.wechatrobot.plugin.tigang;

import cn.hutool.core.util.StrUtil;
import com.yangye.wechatrobot.dispatcher.WxMessage;
import com.yangye.wechatrobot.plugin.AbstractPlugin;
import com.yangye.wechatrobot.reply.ReplyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/12/2 15:41
 */
@Component
@Slf4j
public class TiGangPlugin extends AbstractPlugin {

    @Override
    public boolean support(WxMessage fromMessage) {
        return StrUtil.contains(fromMessage.getMsg(), "提肛小助手") && StrUtil.contains(fromMessage.getMsg(), "rbt");
    }

    @Override
    public void reply(WxMessage fromMessage) {
        ReplyClient.sendGroupMsgAndAt(fromMessage, "感谢参加有痔者关爱行动，准备开始提肛，请对着动图有规律的上提、放松你的菊花");
        ReplyClient.sendEmojiMsg(fromMessage, "https://z3.ax1x.com/2021/12/02/otRYVS.gif");
    }
}
