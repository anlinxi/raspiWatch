package com.faker.raspi.raspiwatch.control;

import com.faker.raspi.raspiwatch.service.WatchSystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 根据表查询
     *
     * @return 返回信息
     */
    @RequestMapping(value = "/test.ilf")
    @ResponseBody
    public Map<String, Object> test(HttpServletRequest request){
        try {
            return watchSystemInfoService.test(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
