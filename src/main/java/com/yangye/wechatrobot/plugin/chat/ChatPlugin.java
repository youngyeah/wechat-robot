package com.yangye.wechatrobot.plugin.chat;

import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yangye.wechatrobot.dispatcher.WxMessage;
import com.yangye.wechatrobot.plugin.AbstractPlugin;
import com.yangye.wechatrobot.reply.ReplyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/12/3 9:41
 */
@Component
@Slf4j
public class ChatPlugin extends AbstractPlugin {

    public static final String QING_YUN_KE_URL = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=";

    @Override
    public boolean support(WxMessage fromMessage) {
        return StrUtil.contains(fromMessage.getMsg(), "rbt");
    }

    @Override
    public void reply(WxMessage fromMessage) {
        String msg = fromMessage.getMsg();
        String resp = HttpUtil.get(QING_YUN_KE_URL + URLEncodeUtil.encode(msg));
        JSONObject jsonObject = JSON.parseObject(resp);
        String content = "我不知道你在说什么";
        if (jsonObject.getInteger("result") == 0) {
            content = jsonObject.getString("content");
            content = StrUtil.replaceIgnoreCase(content, "{br}", "\r");
        }
        ReplyClient.sendGroupMsgAndAt(fromMessage, content);
    }
}
