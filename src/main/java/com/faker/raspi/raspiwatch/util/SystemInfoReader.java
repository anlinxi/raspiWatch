package com.faker.raspi.raspiwatch.util;

import com.faker.raspi.raspiwatch.model.CpuInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SystemInfoReader {

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(SystemInfoReader.class);

    /**
     * 系统换行符
     */
    private static String lineSeparator = System.lineSeparator();

    /**
     * 测试的主方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        lineSeparator = "\n";
        logger.info("树莓派温度:" + String.valueOf(getTemperature()));
        logger.info("树莓派CPU信息:" + getCpuInfo());
        logger.info("树莓派CPU使用信息:" + getCpuUseInfo());
    }

    /**
     * 获取处理器使用情况
     * @return 负载百分比
     */
    public static String getCpuUseInfo() {
        String com1 = "top -n1 | awk '/Cpu\\(s\\):/ {print $2}'";
        return exec(com1) + "%";
    }

    /**
     * 执行命令
     *
     * @param command 命令
     * @return 返回执行显示的结果
     */
    public static String exec(String command) {
        Process process = null;
        try {
            Runtime run = Runtime.getRuntime();
            process = run.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
            if (null != process) {
                return getTextFromStream(new InputStreamReader(process.getErrorStream()));
            }
        }
        InputStreamReader ir = new InputStreamReader(process.getInputStream());
        String text = getTextFromStream(ir);
        return text;

    }

    /**
     * 从流中获取文本
     *
     * @param ir 输入流
     * @return 文本
     */
    public static String getTextFromStream(InputStreamReader ir) {
        LineNumberReader input = new LineNumberReader(ir);
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            while ((line = input.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * 获取cpu信息
     *
     * @return cpu信息实体类
     */
    public static CpuInfo getCpuInfo() {
        /*
        CPU implementer	: 0x41
        CPU architecture: 8
        CPU variant	: 0x0
        CPU part	: 0xd08
        CPU revision	: 3

        Hardware	: BCM2835
        Revision	: c03112
        Serial		: 1000000055194fb2
        Model		: Raspberry Pi 4 Model B Rev 1.2

         */
        List<String> cpuInfoList = getMuliText("/proc/cpuinfo");
        CpuInfo cpuInfo = new CpuInfo();
        cpuInfo.readInfoFromFile(cpuInfoList);
        return cpuInfo;
    }

    /**
     * 获取树莓派温度
     *
     * @return 摄氏度
     */
    public static Float getTemperature() {
        String tempStr = getSimpleText("/sys/class/thermal/thermal_zone0/temp");
        if (tempStr != null) {
            Float temp1 = Float.parseFloat(tempStr);
            //计算出摄氏度
            Float temp2 = temp1 / 1000;
            return temp2;
        }
        return null;
    }

    /**
     * 获取单行文本
     *
     * @param path 路径
     * @return 文本
     */
    public static String getSimpleText(String path) {
        String temp = getText(path);
        if (null != temp && temp.endsWith(lineSeparator)) {
            temp = temp.substring(0, temp.length() - lineSeparator.length());
        }
        return temp;
    }

    /**
     * 获取多行文本
     *
     * @param path 路径
     * @return 多行文本列表
     */
    public static List<String> getMuliText(String path) {
        List<String> stringList = null;
        String temp = getText(path);
        if (null != temp) {
            String[] arr = temp.split(lineSeparator);
            stringList = new ArrayList<>();
            for (String line : arr) {
                if (SystemInfoReader.isNotNull(line)) {
                    stringList.add(line);
                }
            }
        }
        return stringList;
    }

    /**
     * 判断字符串对象是否为空
     *
     * @param object 对象
     * @return 判断结果
     */
    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        } else {
            String str = String.valueOf(object);
            if ("".equals(str.trim())) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 判断字符串对象是否不为空
     *
     * @param object 对象
     * @return 判断结果
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 根据路径获取文件内容
     *
     * @param path 路径
     * @return 文本
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
