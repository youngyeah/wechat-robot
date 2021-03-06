package com.yangye.wechatrobot.plugin.horserace;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/26 16:07
 */
public class RaceHolder {

    public static final Map<String, Race> races = new HashMap<>();

    public Race get(String groupWxid) {
        if (races.containsKey(groupWxid)) {
            return races.get(groupWxid);
        }
        Race race = new Race();
        race.setWxGroupId(groupWxid);
        races.put(groupWxid, race);
        return race;
    }

    public void addHorse(String groupWxid, String memberWxid, String memberName) {
        Race race = this.get(groupWxid);
        Horse horse = new Horse();
        horse.setHorseName(memberName);
        horse.setHorseOwner(memberWxid);
        horse.setDistance(0);
        race.addHorse(horse);
    }

    public int leftToStartRace(String groupWxid) {
        Race race = this.get(groupWxid);
        return race.left();
    }

    public void clear(String groupWxid) {
        races.remove(groupWxid);
    }

    public boolean hasJoinedRace(String groupWxid, String memberWxid) {
        Race race = this.get(groupWxid);
        return race.hasJoinedRace(memberWxid);
    }
}
