package com.faker.raspi.raspiwatch.autorun;

import com.alibaba.fastjson.JSONObject;
import com.faker.raspi.raspiwatch.control.WebSocket;
import com.faker.raspi.raspiwatch.model.RaspiInfo;
import com.faker.raspi.raspiwatch.util.SystemInfoReader;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 继承Application接口后项目启动时会按照执行顺序执行run方法
 * 通过设置Order的value来指定执行的顺序
 */
@Component
@Order(value = 1)
public class StartSendRaspiInfoToWebSocket implements ApplicationRunner {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * WebSocket
     */
    @Autowired
    WebSocket webSocket;

    @ApiModelProperty(value = "间隔时间", notes = "单位毫秒")
    private long time = 1000l;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //自动推送
        while (true) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String json = null;
            try {
                RaspiInfo raspiInfo = SystemInfoReader.getRaspiInfo();
                logger.info(raspiInfo.toString());
                json = JSONObject.toJSONString(raspiInfo);
            } catch (Exception e) {
                logger.error("获取树莓派信息错误:" + e.getMessage());
//                e.printStackTrace();
            }
            try {
                if (json != null) {
                    webSocket.sendMessageAll(json, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
