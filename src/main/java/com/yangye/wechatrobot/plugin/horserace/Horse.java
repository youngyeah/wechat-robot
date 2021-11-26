package com.yangye.wechatrobot.plugin.horserace;

import lombok.Data;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/26 14:56
 */
@Data
public class Horse {

    /**
     * 赛马主人（wxid）
     */
    private String horseOwner;
    /**
     * 赛马头像
     */
    private String horseAvatar;
    /**
     * 赛马名称
     */
    private String horseName;
    /**
     * 距离
     */
    private double distance;
}
