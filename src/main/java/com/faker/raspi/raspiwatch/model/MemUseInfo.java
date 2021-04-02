package com.faker.raspi.raspiwatch.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * pi@raspberrypi:~ $ free
 * total        used        free      shared  buff/cache   available
 * Mem:        3886424     2088688      848676      215660      949060     1467816
 * Swap:        102396       97528        4868
 */
@Data
@ToString
@ApiModel("树莓派硬盘使用信息实体类")
public class MemUseInfo {
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("总容量")
    private String total;
    @ApiModelProperty("已用内存")
    private String used;
    @ApiModelProperty("可用内存")
    private String free;
    @ApiModelProperty("共享内存")
    private String shared;
    @ApiModelProperty("缓存")
    private String cache;
    @ApiModelProperty("可用容量")
    private String available;

    /**
     * 转换内存参数
     * @param line 一行返回的文本信息
     */
    public void getMemInfoFromLine(String line) {
        if (null != line) {
            List<String> stringList = new ArrayList<>();
            String[] arr = line.split(" ");
            for (String word : arr) {
                if (null != word && !"".equals(word.trim())) {
                    stringList.add(word);
                }
            }
            this.name = stringList.get(0).substring(0).substring(0, stringList.get(0).length() - 1);
            this.used = getShowText(stringList.get(2));
            this.free = getShowText(stringList.get(3));
            this.shared = getShowText(stringList.get(4));
            this.cache = getShowText(stringList.get(5));
            this.available = getShowText(stringList.get(6));
        }
    }

    /**
     * 转换显示容量
     *
     * @param numStr
     * @return
     */
    private String getShowText(String numStr) {
        Long num = Long.parseLong(numStr);
        return String.valueOf(num / 1000) + "MB";
    }
}
