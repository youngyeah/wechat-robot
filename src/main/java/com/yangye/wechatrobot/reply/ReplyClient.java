package com.yangye.wechatrobot.reply;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/26 15:08
 */
@Slf4j
public class ReplyClient {

    private static RestTemplate restTemplate = new RestTemplate();

    public static final String REPLY_URL = "http://10.252.117.36:8090/";

    public void reply(ReplyMessage replyMessage) {
        log.info(JSON.toJSONString(replyMessage));
        JSONObject jsonObject = restTemplate.postForObject(REPLY_URL, replyMessage, JSONObject.class);
        log.info(jsonObject.toJSONString());
    }
}
