package com.faker.raspi.raspiwatch.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 树莓派cpu信息
 * /proc/cpuinfo
 * <p>
 * CPU implementer	: 0x41
 * CPU architecture: 8
 * CPU variant	: 0x0
 * CPU part	: 0xd08
 * CPU revision	: 3
 * <p>
 * Hardware	: BCM2835
 * Revision	: c03112
 * Serial		: 1000000055194fb2
 * Model		: Raspberry Pi 4 Model B Rev 1.2
 */
@Data
@ToString
@ApiModel("树莓派CPU信息实体类")
public class CpuInfo {

    @ApiModelProperty("制订版本")
    private String implementer;
    @ApiModelProperty("架构")
    private String architecture;
    @ApiModelProperty("变体型")
    private String variant;
    @ApiModelProperty("部分")
    private String part;
    @ApiModelProperty("修正版本")
    private String revision;

    @ApiModelProperty("硬件版本")
    private String hardware;
    @ApiModelProperty("修正版本")
    private String revision2;
    @ApiModelProperty("序列号")
    private String serial;
    @ApiModelProperty("产品型号")
    private String model;

    /**
     * 从列表中读取出cpu信息
     *
     * @param cpuInfoList 文本列表
     */
    public void readInfoFromFile(List<String> cpuInfoList) {
        for (String cpuinfo : cpuInfoList) {
            if (null != cpuinfo && cpuinfo.contains(":")) {
                String info = cpuinfo.trim().replaceAll("\\t","");
                String[] infoArr = info.split(":");
                if (infoArr.length > 1) {
                    String info1 = infoArr[0];
                    if ("CPU implementer".trim().equals(info1)) {
                        this.implementer = getText(infoArr[1]);
                    } else if ("CPU architecture".trim().equals(info1)) {
                        this.architecture = getText(infoArr[1]);
                    } else if ("CPU variant".trim().equals(info1)) {
                        this.variant = getText(infoArr[1]);
                    } else if ("CPU part".trim().equals(info1)) {
                        this.part = getText(infoArr[1]);
                    } else if ("CPU revision".trim().equals(info1)) {
                        this.revision = getText(infoArr[1]);
                    } else if ("Hardware".trim().equals(info1)) {
                        this.hardware = getText(infoArr[1]);
                    } else if ("Revision".trim().equals(info1)) {
                        this.revision2 = getText(infoArr[1]);
                    } else if ("Serial".trim().equals(info1)) {
                        this.serial = getText(infoArr[1]);
                    } else if ("Model".trim().equals(info1)) {
                        this.model = getText(infoArr[1]);
                    }
                }

            }
        }

    }

    /**
     * 对返回文本做特殊处理
     *
     * @param text 文本
     * @return 处理后的内容
     */
    private String getText(String text) {
        return text;
    }
}
