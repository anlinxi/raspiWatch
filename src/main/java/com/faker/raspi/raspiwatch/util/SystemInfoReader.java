package com.faker.raspi.raspiwatch.util;

import com.faker.raspi.raspiwatch.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * top命令
     */
    private static String top = null;

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
        logger.info("树莓派硬盘占有信息:" + getDiskUseInfo());
        logger.info("树莓派内存使用信息:" + getMemUseInfo());
        logger.info("树莓派进程运行信息:" + getTaskList());
    }

    /**
     * 日期格式化
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取树莓派信息
     *
     * @return 树莓派信息实体类
     */
    public static RaspiInfo getRaspiInfo() {
        RaspiInfo raspiInfo = new RaspiInfo();
        Float temp = getTemperature();
        raspiInfo.setTemperature(String.valueOf(temp) + "℃");
        raspiInfo.setTemperatureMax(temp);
        raspiInfo.setCpuInfo(getCpuInfo());
        raspiInfo.setCpuUseInfo(getCpuUseInfo());
        raspiInfo.setDiskUseInfoList(getDiskUseInfo());
        raspiInfo.setMemUseInfoList(getMemUseInfo());
        raspiInfo.setDateTime(sdf.format(new Date()));
        raspiInfo.setSysStartTime(getSysStartTime());
        raspiInfo.setTaskList(getTaskList());
        return raspiInfo;
    }

    /**
     * 获取top进程信息列表
     *
     * @return
     */
    private static List<TaskInfo> getTaskList() {
        List<TaskInfo> taskInfoList = new ArrayList<>();
        if (null == top) {
            String com1 = "top -b -n 1";
            top = CommandUtil.exeCommand(com1);
        }
        List<String> list = getTextList(top);
        if (list.size() > 6) {
            //只取前20个
            int end = 26;
            if (list.size() < 16) {
                end = list.size();
            }
            for (int i = 6; i < end; i++) {
                TaskInfo taskInfo = getTaskInfo(list.get(i));
                taskInfoList.add(taskInfo);
            }
        }
        return taskInfoList;
    }

    /**
     * 转化解析一行top信息
     * PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND
     * 17895 pi        20   0 5270664   1.6g  27068 S 245.1  42.1  48:32.42 java
     *
     * @param topLine
     * @return
     */
    private static TaskInfo getTaskInfo(String topLine) {
        String[] arr = topLine.split(" ");
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            String data = arr[i];
            if (null != data && !"".equals(data.trim())) {
                dataList.add(data);
            }
        }
        TaskInfo taskInfo = new TaskInfo(dataList.get(0), dataList.get(1), dataList.get(2), dataList.get(3), dataList.get(4), dataList.get(5), dataList.get(6), dataList.get(7), dataList.get(8), dataList.get(9), dataList.get(10), dataList.get(11));
        return taskInfo;
    }

    /**
     * 获取树莓派启动时间
     *
     * @return 单位秒
     */
    private static Long getSysStartTime() {
        String com1 = "cat /proc/uptime";
        String df = CommandUtil.exeCommand(com1);
        String startTimeStr = df.split("\\.")[0];
        Long startTime = Long.parseLong(startTimeStr);
        return startTime;
    }

    /**
     * 获取树莓派内存使用使用情况
     *
     * @return
     */
    private static List<MemUseInfo> getMemUseInfo() {
        String com1 = "free";
        String df = CommandUtil.exeCommand(com1);
        List<String> list = getTextList(df);
        List<MemUseInfo> diskUseInfoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                continue;
            }
            String line = list.get(i);
            MemUseInfo memUseInfo = new MemUseInfo();
            memUseInfo.getMemInfoFromLine(line);
            diskUseInfoList.add(memUseInfo);
        }
        return diskUseInfoList;
    }

    /**
     * 获取树莓派硬盘占用使用情况
     *
     * @return
     */
    private static List<DiskUseInfo> getDiskUseInfo() {
        String com1 = "df -h /";
        String df = CommandUtil.exeCommand(com1);
        List<String> list = getTextList(df);
        List<DiskUseInfo> diskUseInfoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                continue;
            }
            String line = list.get(i);
            DiskUseInfo diskUseInfo = new DiskUseInfo();
            diskUseInfo.getDiskInfoFromLine(line);
            diskUseInfoList.add(diskUseInfo);
        }
        return diskUseInfoList;
    }

    /**
     * 获取处理器使用情况
     *
     * @return 负载百分比
     */
    public static String getCpuUseInfo() {
        if (null == top) {
            String com1 = "top -b -n 1";
            top = CommandUtil.exeCommand(com1);
        }
        //md正则太难了，还不如截取呢...
        String cpuStart = "%Cpu(s): ";
        String cpuUse = top.substring(top.indexOf(cpuStart) + cpuStart.length(), top.indexOf(" us,")).trim();
        return cpuUse + "%";
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
        String arm_freq = CommandUtil.exeCommand("vcgencmd get_config arm_freq");
        String armFreq = arm_freq.trim().split("=")[1];
        cpuInfo.setArmFreq(armFreq);
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
            stringList = getTextList(temp);
        }
        return stringList;
    }

    /**
     * 文本换行符分行
     *
     * @param text
     * @return
     */
    public static List<String> getTextList(String text) {
        List<String> stringList = null;
        if (null != text) {
            String[] arr = text.split(lineSeparator);
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
