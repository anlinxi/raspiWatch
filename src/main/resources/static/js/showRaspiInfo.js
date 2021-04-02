/**
 * 显示树莓派信息到页面上
 */


function showRaspiInfo(json) {
    // console.log('树莓派当前信息', json)
    showTemperature(json.temperature)
    showMen(json)
    showTime(json)
    showCpu(json)
    showDisk(json)
}

/**
 * 显示硬盘占用信息
 * @param json
 */
function showDisk(json) {
    var free = json.diskUseInfoList[0].usable
    $("#disk-percent").text(json.diskUseInfoList[0].percentSpent.replace("%", ''));
    $("#disk-free").text(free);
}

/**
 * 显示服务器时间
 * @param json
 */
function showTime(json) {
    var dateTime = json.dateTime
    var date = dateTime.split(' ')[0]
    //不要秒，秒不太准确
    var time = dateTime.split(' ')[1].substr(0, 5)
    $("#time").text(time)
    $("#date").text(date)
    //显示ip
    var host1 = window.location.host;
    var hostip = host1.split(":")[0]
    $("#hostip").text(hostip)
}

/**
 * 显示温度
 * @param temperature
 */
function showTemperature(temperature) {
    if (temperature) {
        temperature = temperature.replace("℃", "")
        $("#cpu-temp").text(temperature)
    }
}

/**
 * 显示内存信息
 * @param json
 */
function showMen(json) {
    var memUseInfoList = json.memUseInfoList
    for (var i = 0; i < memUseInfoList.length; i++) {
        var memtemp = memUseInfoList[i]
        if (memtemp.name == 'Mem') {
            $("#mem-cached").text(memtemp.cache.replace('MB', ''));
            var percent = 0
            var total = parseInt(memtemp.total.replace('MB', ''))
            var used = parseInt(memtemp.used.replace('MB', ''))
            $("#mem-free").text(memtemp.free.replace('MB', ''));
            percent = used / total * 100
            percent = percent.toFixed(2)
            $("#mem-percent").text(percent);

            showMemCharts(total, used)

            $("#mem-real-percent").text(percent);
            $("#mem-real-free").text(memtemp.free.replace('MB', ''));
        } else if (memtemp.name == 'Swap') {
            $("#mem-swap-total").text(memtemp.total.replace('MB', ''));
            var total = parseInt(memtemp.total.replace('MB', ''))
            var used = parseInt(memtemp.used.replace('MB', ''))
            percent = used / total * 100
            percent = percent.toFixed(2)
            $("#mem-swap-percent").text(percent);
            $("#mem-swap-free").text(memtemp.free.replace('MB', ''));
        }
    }
}

/**
 * 显示处理器相关信息
 * @param json
 */
function showCpu(json) {
    var cpuUse = json.cpuUseInfo.replace('%', '')
    cpuUse = parseFloat(cpuUse)
    showCpuCharts(cpuUse)
    $("#cpu-count").text(4)
    $("#cpu-freq").text(json.cpuInfo.armFreq)
    $("#cpu-model").text(json.cpuInfo.model)
    $("#user").text(json.cpuInfo.hardware)
    $("#os").text(json.cpuInfo.revision2)
}

//hcharts参数
var gaugeOptions = {
    chart: {type: "solidgauge"},
    title: null,
    pane: {
        center: ["50%", "85%"],
        size: "140%",
        startAngle: -90,
        endAngle: 90,
        background: {
            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || "#FFFFFF",
            innerRadius: "60%",
            outerRadius: "100%",
            shape: "arc"
        }
    },
    tooltip: {enabled: false},
    yAxis: {
        stops: [[0.1, "#55BF3B"], [0.5, "#DDDF0D"], [0.9, "#DF5353"]],
        lineWidth: 0,
        minorTickInterval: null,
        tickAmount: 2,
        title: {y: -70},
        labels: {y: 16}
    },
    plotOptions: {solidgauge: {dataLabels: {y: 5, borderWidth: 0, useHTML: true}}}
};

///内存仪表盘
var chartRAM = null;

/**
 * 显示内存仪表盘
 * @param total
 * @param used
 */
function showMemCharts(total, used) {
    if (!chartRAM) {
        chartRAM = Highcharts.chart("container-mem", Highcharts.merge(gaugeOptions, {
            yAxis: {
                min: 0,
                max: total,
                title: {text: ""}
            },
            series: [{
                name: "MEMORY",
                data: [0],
                dataLabels: {format: '<div style="text-align:center"><span style="font-size:25px;color:' + ((Highcharts.theme && Highcharts.theme.contrastTextColor) || "black") + '">{y:.1f}</span><br/>' + '<span style="font-size:12px;color:silver">MB</span></div>'},
                tooltip: {valueSuffix: " MB"},
                points: []
            }]
        }));
    }
    var point = chartRAM.series[0].points[0];
    point.update(used)
}

var chartCPU = null;

/**
 * 显示cpu仪表盘
 * @param used
 */
function showCpuCharts(used) {
    if (!chartCPU) {
        chartCPU = Highcharts.chart("container-cpu", Highcharts.merge(gaugeOptions, {
            yAxis: {
                min: 0,
                max: 100,
                title: {text: ""}
            },
            series: [{
                name: "CPU",
                data: [0],
                dataLabels: {format: '<div style="text-align:center"><span style="font-size:28px;color:' + ((Highcharts.theme && Highcharts.theme.contrastTextColor) || "black") + '">{y}</span>' + '<span style="font-size:12px;color:silver">%</span></div>'},
                tooltip: {valueSuffix: " %"}
            }]
        }));
    }
    var point = chartCPU.series[0].points[0];
    point.update(used)
}