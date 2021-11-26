package com.yangye.wechatrobot.plugin.horserace;

import com.yangye.wechatrobot.dispatcher.WxMessage;
import com.yangye.wechatrobot.plugin.AbstractPlugin;
import com.yangye.wechatrobot.plugin.PluginCmd;
import com.yangye.wechatrobot.reply.ReplyClient;
import com.yangye.wechatrobot.reply.ReplyEvent;
import com.yangye.wechatrobot.reply.ReplyMessage;
import org.springframework.stereotype.Component;

/**
 * 赛马游戏
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/26 14:57
 */
@Component
public class HorseRacePlugin extends AbstractPlugin {

    public static final RaceHolder raceHolder = new RaceHolder();

    @Override
    public boolean support(PluginCmd cmd) {
        return cmd == PluginCmd.HORSE_RACE;
    }

    @Override
    public void reply(WxMessage fromMessage) {
        raceHolder.addHorse(fromMessage.getFromWxid(), fromMessage.getFinalFromWxid(), fromMessage.getFinalFromName());

        ReplyMessage replyMessage = new ReplyMessage();
        replyMessage.setRobot_wxid(fromMessage.getRobotWxid());
        replyMessage.setGroup_wxid(fromMessage.getFromWxid());
        replyMessage.setMember_wxid(fromMessage.getFinalFromWxid());
        replyMessage.setMember_name(fromMessage.getFinalFromName());
        replyMessage.setEvent(ReplyEvent.SendGroupMsgAndAt.toString());
        replyMessage.setMsg("参加赛马游戏成功，等待游戏开始");
        ReplyClient replyClient = new ReplyClient();
        replyClient.reply(replyMessage);
    }
}
