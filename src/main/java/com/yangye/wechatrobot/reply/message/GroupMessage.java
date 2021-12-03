package com.yangye.wechatrobot.reply.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *     {
 *          "msg": "机器人的消息",
 *         "event": "SendGroupMsgAndAt",
 *         "robot_wxid": "wxid_cb0euuiz5ki122",
 *         "member_wxid": "xueloveling",
 *         "member_name": "朱孟学",
 *         "group_wxid": "4695402856@chatroom"
 *     }
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/26 15:04
 */
@Data
public class GroupMessage extends BaseMessage{

    @JsonProperty("event")
    private String event;

    @JsonProperty("robot_wxid")
    private String robot_wxid;

    @JsonProperty("group_wxid")
    private String group_wxid;

    @JsonProperty("member_wxid")
    private String member_wxid;

    @JsonProperty("member_name")
    private String member_name;

    @JsonProperty("msg")
    private String msg;



}
