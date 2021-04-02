package com.faker.raspi.raspiwatch.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @program: raspiWatch
 * @description: BaseController
 * @author: 淡梦如烟
 * @create: 2021-03-25 16:15
 */
@Controller
public class BaseController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/")
    public String index() {
        logger.info("访问首页" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "redirect:/webSocketTest.html";
    }
}