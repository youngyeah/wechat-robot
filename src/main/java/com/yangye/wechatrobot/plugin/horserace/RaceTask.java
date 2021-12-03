package com.yangye.wechatrobot.plugin.horserace;

import com.yangye.wechatrobot.dispatcher.WxMessage;
import com.yangye.wechatrobot.reply.ReplyClient;
import lombok.Data;

import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/12/1 18:46
 */
@Data
public class RaceTask implements Runnable{

    private Race race;

    private int count;

    private WxMessage fromMessage;

    private final Random r = new Random();

    public RaceTask(Race race, WxMessage fromMessage) {
        this.race = race;
        this.fromMessage = fromMessage;
        this.count = 0;
    }

    @Override
    public void run() {
        List<Horse> horses = race.getHorses();
        for (Horse horse : horses) {
            int oldDis = horse.getDistance();
            int addDis = r.nextInt(20) + 1;
            int newDis = oldDis + addDis;
            horse.setDistance(newDis);
        }
        ReplyClient.sendTextMsg(fromMessage, RaceMsgBuilder.buildRacingMsg(race.getHorses()));
        count = count + 1;
    }

    public boolean isFinish() {
        return count == 5;
    }
}
