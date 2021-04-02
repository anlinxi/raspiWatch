package com.faker.raspi.raspiwatch.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * pi@raspberrypi:~ $ df -h /
 * 文件系统        容量  已用  可用 已用% 挂载点
 * /dev/root        59G   21G   36G   36% /
 */
@Data
@ToString
@ApiModel("树莓派硬盘使用信息实体类")
public class DiskUseInfo {
    @ApiModelProperty("文件系统")
    private String diskPath;
    @ApiModelProperty("容量")
    private String capacity;
    @ApiModelProperty("已用")
    private String used;
    @ApiModelProperty("可用")
    private String usable;
    @ApiModelProperty("已用百分比")
    private String percentSpent;

    /**
     * 从命令行中提取参赛
     *
     * @param line 返回的一行命令
     */
    public void getDiskInfoFromLine(String line) {
        if (null != line) {
            List<String> stringList = new ArrayList<>();
            String[] arr = line.split(" ");
            for (String word : arr) {
                if (null != word && !"".equals(word.trim())) {
                    stringList.add(word);
                }
            }
            this.diskPath = stringList.get(0);
            this.capacity = stringList.get(1) + "B";
            this.used = stringList.get(2) + "B";
            this.usable = stringList.get(3) + "B";
            this.percentSpent = stringList.get(4);
        }
    }
}
