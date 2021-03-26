package com.faker.raspi.raspiwatch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class SystemInfoReader {

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(SystemInfoReader.class);

    /**
     * 测试的主方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        logger.info(getSimpleText("/sys/class/thermal/thermal_zone0/temp"));
    }

    /**
     * 获取单行文本
     *
     * @param path
     * @return
     */
    public static String getSimpleText(String path) {
        String temp = getText(path);
        if (null != temp && temp.endsWith(System.lineSeparator())) {
            temp = temp.substring(0, temp.length() - System.lineSeparator().length());
        }
        return temp;
    }

    /**
     * 根据路径获取文件内容
     *
     * @param path
     * @return
     */
    public static String getText(String path) {
        File file = new File(path);
        if (file.exists()) {
            String temp = TxtReader.readTextFile(file);
            return temp;
        }
        return null;
    }
}
