package com.yangye.wechatrobot.reply;

import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yangye.wechatrobot.dispatcher.WxMessage;
import com.yangye.wechatrobot.reply.message.BaseMessage;
import com.yangye.wechatrobot.reply.message.FileMessage;
import com.yangye.wechatrobot.reply.message.GroupMessage;
import com.yangye.wechatrobot.reply.message.TextMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/26 15:08
 */
@Slf4j
public class ReplyClient {

    public static final String REPLY_URL = "http://nas.aizaozao.xyz:8090/";

    public static void reply(BaseMessage message) {
        JSONObject obj = JSONUtil.parseObj(message);
        String body = HttpRequest.post(REPLY_URL).timeout(-1).charset(CharsetUtil.CHARSET_GBK).body(obj.toString()).execute().body();
        log.info("发送消息结果：" + body);
    }

    public static void sendGroupMsgAndAt(WxMessage fromMessage, String replyMsg) {
        GroupMessage groupMessage = new GroupMessage();
        groupMessage.setRobot_wxid(fromMessage.getRobotWxid());
        groupMessage.setGroup_wxid(fromMessage.getFromWxid());
        groupMessage.setMember_wxid(fromMessage.getFinalFromWxid());
        groupMessage.setMember_name(UnicodeUtil.toUnicode(fromMessage.getFinalFromName()));
        groupMessage.setEvent(ReplyEvent.SendGroupMsgAndAt.toString());
        groupMessage.setMsg(replyMsg);
        ReplyClient.reply(groupMessage);
    }

    public static void sendTextMsg(WxMessage fromMessage, String replyMsg) {
        TextMessage textMessage = new TextMessage();
        textMessage.setTo_wxid(fromMessage.getFromWxid());
        textMessage.setRobot_wxid(fromMessage.getRobotWxid());
        textMessage.setEvent(ReplyEvent.SendTextMsg.toString());
        textMessage.setMsg(replyMsg);
        ReplyClient.reply(textMessage);
    }

    public static void sendEmojiMsg(WxMessage fromMessage, String emojiUrl) {
        FileMessage fileMessage = new FileMessage();
        fileMessage.setTo_wxid(fromMessage.getFromWxid());
        fileMessage.setRobot_wxid(fromMessage.getRobotWxid());
        fileMessage.setEvent(ReplyEvent.SendEmojiMsg.toString());

        FileMessage.FileMsg fileMsg = new FileMessage.FileMsg(emojiUrl);
        fileMessage.setMsg(fileMsg);
        ReplyClient.reply(fileMessage);
    }
}
