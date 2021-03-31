package com.faker.raspi.raspiwatch.util;

import org.apache.commons.exec.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 执行系统命令工具类
 *
 * @author https://blog.csdn.net/chen_2890
 */
public class CommandUtil {

    private static Logger logger = LoggerFactory.getLogger(CommandUtil.class.getSimpleName());
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final Long TIMEOUT = 10000L;

    /**
     * 执行指定命令
     *
     * @param command 命令
     * @return 命令执行完成返回结果
     * @throws RuntimeException 失败时抛出异常，由调用者捕获处理
     */
    public synchronized static String exeCommand(String command) throws RuntimeException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int exitCode = exeCommand(command, out);
            if (exitCode == 0) {
                logger.info("命令运行成功:" + System.currentTimeMillis());
            } else {
                logger.info("命令运行失败:" + System.currentTimeMillis());
            }
            return out.toString(DEFAULT_CHARSET);
        } catch (Exception e) {
            logger.info(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * 执行指定命令，输出结果到指定输出流中
     *
     * @param command 命令
     * @param out     执行结果输出流
     * @return 执行结果状态码：执行成功返回0
     * @throws ExecuteException 失败时抛出异常，由调用者捕获处理
     * @throws IOException      失败时抛出异常，由调用者捕获处理
     */
    public synchronized static int exeCommand(String command, OutputStream out) throws ExecuteException, IOException {
        CommandLine commandLine = CommandLine.parse(command);
        PumpStreamHandler pumpStreamHandler = null;
        if (null == out) {
            pumpStreamHandler = new PumpStreamHandler();
        } else {
            pumpStreamHandler = new PumpStreamHandler(out);
        }
        // 设置超时时间为10秒
        ExecuteWatchdog watchdog = new ExecuteWatchdog(TIMEOUT);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(pumpStreamHandler);
        executor.setWatchdog(watchdog);
        return executor.execute(commandLine);
    }

    public static void main(String[] args) {
        logger.info(exeCommand("pwd"));
        logger.info(exeCommand("top -b -n 1 | awk '/Cpu\\(s\\):/ {print $2}'"));
    }

}
