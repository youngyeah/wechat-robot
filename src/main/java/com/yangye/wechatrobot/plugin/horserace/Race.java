package com.yangye.wechatrobot.plugin.horserace;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/26 14:56
 */
@Data
public class Race {

    public static final int CAN_START_RACE_COUNT = 3;

    public static final List<String> avatars = Arrays.asList("[猪头]", "[呲牙]",
            "[白眼]", "[可怜]", "[生病]");

    private String wxGroupId;

    private List<Horse> horses;

    public void addHorse(Horse horse) {
        if (horses == null) {
            horses = new ArrayList<>();
        }
        horse.setHorseAvatar(avatars.get(horses.size()));
        this.horses.add(horse);
    }

    public int left() {
        if (horses == null) {
            return CAN_START_RACE_COUNT;
        }
        return CAN_START_RACE_COUNT - horses.size();
    }

    public boolean hasJoinedRace(String memberWxid) {
        return horses.stream().anyMatch(v -> StrUtil.equals(v.getHorseOwner(), memberWxid));
    }

}
