package com.faker.raspi.raspiwatch.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND
 * 17895 pi        20   0 5270664   1.6g  27068 S 245.1  42.1  48:32.42 java
 */
@Data
@ToString
@ApiModel("树莓派TOP命令进程信息实体类")
public class TaskInfo {

    @ApiModelProperty(value = "进程pid标识",notes = "PID")
    private String pid;

    @ApiModelProperty(value = "启动进程的用户",notes = "USER")
    private String user;

    @ApiModelProperty(value = "PR",notes = "PR")
    private String pr;

    @ApiModelProperty(value = "NI",notes = "NI")
    private String ni;

    @ApiModelProperty(value = "VIRT",notes = "VIRT")
    private String virt;

    @ApiModelProperty(value = "资源",notes = "RES")
    private String res;

    @ApiModelProperty(value = "SHR",notes = "SHR")
    private String shr;

    @ApiModelProperty(value = "S",notes = "S")
    private String s;

    @ApiModelProperty(value = "处理器占用比例",notes = "CPU")
    private String cpu;

    @ApiModelProperty(value = "内存占用比例",notes = "MEM")
    private String mem;

    @ApiModelProperty(value = "启动时间",notes = "TIME")
    private String time;

    @ApiModelProperty(value = "命令",notes = "COMMAND")
    private String command;

    public TaskInfo() {
    }

    public TaskInfo(String pid, String user, String pr, String ni, String virt, String res, String shr, String s, String cpu, String mem, String time, String command) {
        this.pid = pid;
        this.user = user;
        this.pr = pr;
        this.ni = ni;
        this.virt = virt;
        this.res = res;
        this.shr = shr;
        this.s = s;
        this.cpu = cpu;
        this.mem = mem;
        this.time = time;
        this.command = command;
    }
}
