package com.yangye.wechatrobot.dispatcher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/23 13:39
 */
@Data
public class WxMessage {

    /**
     * 事件标示
     */
    @JsonProperty("event")
    private String event;

    /**
     * 机器人wxid
     */
    @JsonProperty("robot_wxid")
    private String robotWxid;

    /**
     * 机器人昵称，一般为空
     */
    @JsonProperty("robot_name")
    private String robotName;

    /**
     * 1 文本消息 
     * 3 图片消息 
     * 34 语音消息  
     * 42 名片消息
     * 43 视频
     * 47 动态表情
     * 48 地理位置
     * 49 分享链接
     * 2000 转账
     * 2001 红包
     * 2002 小程序
     * 2003 群邀请
     */
    @JsonProperty("type")
    private Integer type;

    /**
     * 群id，群消息事件才有
     */
    @JsonProperty("from_wxid")
    private String fromWxid;

    /**
     * 群名字
     */
    @JsonProperty("from_name")
    private String fromName;

    /**
     * 发该消息的用户微信id
     */
    @JsonProperty("final_from_wxid")
    private String finalFromWxid;

    /**
     * 微信昵称
     */
    @JsonProperty("final_from_name")
    private String finalFromName;

    /**
     * 接收消息的人id，（一般是机器人收到了，也有可能是机器人发出的消息，别人收到了，那就是别人）
     */
    @JsonProperty("to_wxid")
    private String toWxid;

    /**
     * 消息内容(string/array) 使用时候根据不同的事件标示来定义这个值，字符串类型或者数据类型
     */
    @JsonProperty("msg")
    private String msg;

    /**
     * "money":0.01 //金额，只有"EventReceivedTransfer"事件才有该参数
     */
    @JsonProperty("money")
    private double money;
}
