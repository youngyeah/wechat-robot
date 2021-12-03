package com.yangye.wechatrobot.plugin.tiangou;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
 * @date: 2021/12/2 15:16
 */
@Component
@Slf4j
public class TianGouPlugin extends AbstractPlugin {

    public static final String TIAN_GOU_URL = "http://api.tianapi.com/tiangou/index?key=bd6ffe464e15033891c72a678566b00c";

    @Override
    public boolean support(WxMessage fromMessage) {
        return StrUtil.contains(fromMessage.getMsg(), "今日舔狗") && StrUtil.contains(fromMessage.getMsg(), "rbt");
    }

    @Override
    public void reply(WxMessage fromMessage) {
        String resp = HttpUtil.get(TIAN_GOU_URL);
        JSONObject jsonObject = JSON.parseObject(resp);
        if (jsonObject.getInteger("code") == 200) {
            JSONArray newslist = jsonObject.getJSONArray("newslist");
            JSONObject news = (JSONObject) newslist.get(0);
            String content = news.getString("content");
            ReplyClient.sendGroupMsgAndAt(fromMessage, content);
        }
    }
}
