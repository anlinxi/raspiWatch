package com.faker.raspi.raspiwatch.service;

import com.faker.raspi.raspiwatch.util.TxtReader;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: raspiWatch
 * @description: 监控树莓派系统运行信息服务层
 * @author: 淡梦如烟
 * @create: 2021-03-25 16:15
 */
@Service
public class WatchSystemInfoService {
    /**
     * 测试方法
     *
     * @param request 请求
     * @return
     */
    public Map<String, Object> test(HttpServletRequest request) {
        Map reslut = new HashMap();
        String path = request.getParameter("path");
        try {
            if (null == path | "".equals(path)) {
                path = "/sys/class/thermal/thermal_zone0/temp";
            }
            String temp = TxtReader.readTextFile(new File(path));
            reslut.put("temp", temp);
        } catch (Exception e) {
            e.printStackTrace();
            reslut.put("error", e.getMessage());
        }
        return reslut;
    }
}
