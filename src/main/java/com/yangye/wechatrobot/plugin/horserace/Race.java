package com.yangye.wechatrobot.plugin.horserace;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/26 14:56
 */
@Data
public class Race {

    private String wxGroupId;

    private List<Horse> horses;

    public void addHorse(Horse horse) {
        if (horses == null) {
            horses = new ArrayList<>();
        }
        this.horses.add(horse);
    }
}
