package com.yangye.wechatrobot.reply.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/12/1 11:11
 */
@Data
public class TextMessage extends BaseMessage{

    private String event;

    private String robot_wxid;

    private String to_wxid;

    private String msg;
}
