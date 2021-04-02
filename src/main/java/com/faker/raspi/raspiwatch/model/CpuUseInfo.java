package com.faker.raspi.raspiwatch.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * cat /proc/stat
 * <p>
 * cpu1 123459 0 21283 1427300 2861 0 651 0 0 0
 * cpu2 126849 0 19355 1426990 2797 0 662 0 0 0
 * cpu3 118614 0 18401 1435874 2958 0 680 0 0 0
 * intr 18636016 0 30802 5035462 0 0 0 122289 0 0 0 0 8181899 0 0 0 0 0 0 240973 0 0 46296 0 0 56733 0 0 0 0 0 0 11865 3221141 949396 0 0 0 0 0 77255 0 0 0 0 0 0 661867 0 38 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * ctxt 24318775
 * btime 1616749177
 * processes 7833
 * procs_running 1
 * procs_blocked 0
 * softirq 7342671 3 857956 2 314488 128835 0 1985437 1886877 1173 2167900
 */
@Data
@ToString
@ApiModel("树莓派CPU实时使用信息实体类")
public class CpuUseInfo {

    @ApiModelProperty("处理器1")
    private String cpu1;
    @ApiModelProperty("处理器2")
    private String cpu2;
    @ApiModelProperty("处理器3")
    private String cpu3;
    @ApiModelProperty("处理器1")
    private String intr;
    @ApiModelProperty("处理器1")
    private String ctxt;
    @ApiModelProperty("处理器1")
    private String btime;
    @ApiModelProperty("处理器1")
    private String processes;
    @ApiModelProperty("处理器1")
    private String procsRunning;
    @ApiModelProperty("处理器1")
    private String procsBlocked;
    @ApiModelProperty("处理器1")
    private String softirq;
}
