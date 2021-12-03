package com.yangye.wechatrobot.plugin.horserace;

import cn.hutool.core.util.StrUtil;
import com.yangye.wechatrobot.dispatcher.WxMessage;
import com.yangye.wechatrobot.plugin.AbstractPlugin;
import com.yangye.wechatrobot.reply.ReplyClient;
import com.yangye.wechatrobot.utils.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 赛马游戏
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/26 14:57
 */
@Component
@Slf4j
public class HorseRacePlugin extends AbstractPlugin {

    public static final RaceHolder raceHolder = new RaceHolder();

    public ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

    @Override
    public boolean support(WxMessage fromMessage) {
        return StrUtil.contains(fromMessage.getMsg(), "赛马游戏") && StrUtil.contains(fromMessage.getMsg(), "rbt");
    }

    @Override
    public void reply(WxMessage fromMessage) {
        int leftHorse = raceHolder.leftToStartRace(fromMessage.getFromWxid());
        if (leftHorse > 0) {
            raceHolder.addHorse(fromMessage.getFromWxid(), fromMessage.getFinalFromWxid(), fromMessage.getFinalFromName());
            leftHorse = raceHolder.leftToStartRace(fromMessage.getFromWxid());
            if (leftHorse > 0) {
                ReplyClient.sendGroupMsgAndAt(fromMessage, "参加赛马游戏成功，再等" + raceHolder.leftToStartRace(fromMessage.getFromWxid()) + "人就可以开始了");
            } else {
                Race race = raceHolder.get(fromMessage.getFromWxid());
                ReplyClient.sendGroupMsgAndAt(fromMessage, "参加赛马游戏成功，请等待游戏开始");
                ThreadUtil.sleepSeconds(2);
                ReplyClient.sendTextMsg(fromMessage, RaceMsgBuilder.buildStartMsg(race));
                // 添加定时任务
                RaceTask raceTask = new RaceTask(race, fromMessage);
                RunnableScheduledFuture<?> scheduledFuture = (RunnableScheduledFuture<?>) executor.scheduleWithFixedDelay(raceTask, 5, 10, TimeUnit.SECONDS);
                while (true) {
                    if (raceTask.isFinish()) {
                        executor.remove(scheduledFuture);
                        raceHolder.clear(fromMessage.getFromWxid());
                        ReplyClient.sendTextMsg(fromMessage, RaceMsgBuilder.buildFinishMsg(race.getHorses()));
                        break;
                    }
                    ThreadUtil.sleepSeconds(1);
                }
            }
        } else {
            ReplyClient.sendGroupMsgAndAt(fromMessage, "赛马游戏已经开始，请等待下一场");
        }


    }
}
