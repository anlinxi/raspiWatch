package com.faker.raspi.raspiwatch.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

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

    public void getDiskInfoFromLine(String line) {

    }
}
