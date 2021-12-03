package com.yangye.wechatrobot.reply.message;

import cn.hutool.core.io.FileUtil;
import lombok.Data;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/12/2 15:46
 */
@Data
public class FileMessage extends BaseMessage {

    private String event;

    private String robot_wxid;

    private String to_wxid;

    private FileMsg msg;

    @Data
    public static class FileMsg {

        public FileMsg(String url) {
            this.url = url;
            this.name = FileUtil.getName(url);
        }

        private String url;

        private String name;
    }

    public static void main(String[] args) {
        System.out.println(FileUtil.getSuffix("https://z3.ax1x.com/2021/12/02/otRYVS.gif"));
        System.out.println(FileUtil.getName("https://z3.ax1x.com/2021/12/02/otRYVS.gif"));
    }
}
