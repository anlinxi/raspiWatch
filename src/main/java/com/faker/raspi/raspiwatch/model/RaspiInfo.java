package com.faker.raspi.raspiwatch.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@ApiModel("树莓派整体信息实体类")
public class RaspiInfo {

    @ApiModelProperty("树莓派温度")
    private String temperature;

    @ApiModelProperty("树莓派历时最高温度")
    private Float temperatureMax;

    @ApiModelProperty("树莓派CPU信息")
    private CpuInfo cpuInfo;

    @ApiModelProperty("树莓派CPU使用负载百分比")
    private String cpuUseInfo;

    @ApiModelProperty("树莓派硬盘占有信息")
    private List<DiskUseInfo> diskUseInfoList;

    @ApiModelProperty("树莓派内存使用信息")
    private List<MemUseInfo> memUseInfoList;

    @ApiModelProperty("树莓派时间")
    private String dateTime;

    @ApiModelProperty(value = "树莓派启动时间", notes = "单位秒")
    private Long sysStartTime;

}
