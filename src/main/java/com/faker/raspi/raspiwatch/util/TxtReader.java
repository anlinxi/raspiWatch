package com.faker.raspi.raspiwatch.util;

import java.io.*;

/**
 * @program: jeesite4.1.5
 * @description: TxtReader
 * @author: 淡梦如烟
 * @create: 2020-04-17 14:55
 */
public class TxtReader {

    /**
     * 读取文本文件
     *
     * @param file 文件
     * @return
     */
    public static String readTextFile(File file) {
        return readTextFile(file, "UTF-8");
    }

    /**
     * 写入文本文件
     *
     * @param file 文件
     * @param text 内容
     */
    public static void writeTextFile(File file, String text) {
        writeTextFile(file, text, "UTF-8");
    }

    /**
     * 写入文本文件
     *
     * @param file        文件
     * @param text        内容
     * @param charsetName 编码
     */
    public static void writeTextFile(File file, String text, String charsetName) {
        //写入文件
        file.delete();
        BufferedWriter writer = null;
        try {
            file.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charsetName));
            writer.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文本文件
     *
     * @param file        文件
     * @param charsetName 编码
     * @return
     */
    public static String readTextFile(File file, String charsetName) {
        //找到文件，读取文件
        BufferedReader bufferedReader1 = null;
        StringBuffer content = new StringBuffer();
        try {
            bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
            String line = null;
            while ((line = bufferedReader1.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader1 != null) {
                try {
                    bufferedReader1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}