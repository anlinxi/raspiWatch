package com.faker.raspi.raspiwatch.control;

import com.faker.raspi.raspiwatch.service.WatchSystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @program: raspiWatch
 * @description: 监控树莓派系统运行信息控制层
 * @author: 淡梦如烟
 * @create: 2021-03-25 16:15
 */
@Controller
public class WatchSystemInfoController {

    /**
     * 监控树莓派系统运行信息服务层
     */
    @Autowired
    WatchSystemInfoService watchSystemInfoService;
}
