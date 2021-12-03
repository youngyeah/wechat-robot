package com.yangye.wechatrobot.plugin.horserace;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/12/2 14:11
 */
@Slf4j
public class RaceMsgBuilder {

    public static String buildStartMsg(Race race) {
        StringBuilder sb = new StringBuilder();
        List<Horse> horses = race.getHorses();
        sb.append("赛马比赛开始！\r");
        sb.append("===================\r");
        sb.append("本局参与者：\r");
        for (Horse horse : horses) {
            sb.append("- " + horse.getHorseAvatar() + " " + horse.getHorseName());
            sb.append("\r");
        }
        return sb.toString();
    }

    public static String buildFinishMsg(List<Horse> horses) {
        StringBuilder sb = new StringBuilder();
        sb.append("赛马比赛结束\r");
        sb.append("====================\r");
        List<Horse> sortedList = horses.stream().sorted((o1, o2) -> o2.getDistance() - o1.getDistance()).collect(Collectors.toList());
        sb.append("恭喜")
                .append(sortedList.get(0).getHorseAvatar())
                .append(sortedList.get(0).getHorseName())
                .append("获得本场冠军[强][烟花][烟花]\r");
        sb.append("恭喜")
                .append(sortedList.get(1).getHorseAvatar())
                .append(sortedList.get(1).getHorseName())
                .append("获得本场亚军[拳头]\r");
        sb.append("恭喜")
                .append(sortedList.get(2).getHorseAvatar())
                .append(sortedList.get(2).getHorseName())
                .append("获得本场季军[弱]");

        return sb.toString();
    }

    public static String buildRacingMsg(List<Horse> horses) {
        StringBuilder sb = new StringBuilder();
        sb.append("赛马比赛实况直播\r");
        sb.append("====================\r");
        for (Horse horse : horses) {
            sb.append(horse.getHorseAvatar())
                    .append(" ")
                    .append(horse.getHorseName())
                    .append("：")
                    .append(horse.getDistance())
                    .append("\r");
        }
        return sb.toString();
    }
}
