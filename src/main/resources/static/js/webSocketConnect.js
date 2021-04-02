/**
 * 2021年4月2日12:25:00
 * websocket连接js
 */
//定义全局变量websocket
var websocket = null;

//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    //连接WebSocket节点
    websocket = getNewWebSocket();
} else {
    alert('Not support websocket')
}

function getNewWebSocket() {
    var _websocket = new WebSocket("ws://" + host + "/connectWebSocket/" + new Date().getTime());
    //连接发生错误的回调方法
    _websocket.onerror = function () {
        setTimeout(function () {
            websocket = getNewWebSocket();
        }, 5000)
    };
    //接收到消息的回调方法
    _websocket.onmessage = function (event) {
        var data = JSON.parse(event.data)
        showRaspiInfo(data)
        // setMessageInnerHTML(event.data);
    }
    //连接成功建立的回调方法
    _websocket.onopen = function (event) {
        // setMessageInnerHTML("open");
    }


    //接收到消息的回调方法
    _websocket.onmessage = function (event) {
        var data = JSON.parse(event.data)
        showRaspiInfo(data)
        // setMessageInnerHTML(event.data);
    }


    //连接关闭的回调方法
    _websocket.onclose = function () {
        // setMessageInnerHTML("close");
    }
    return _websocket;
}


//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    websocket.close();
}


//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}


//关闭连接
function closeWebSocket() {
    websocket.close();
}


//发送消息
function send(message) {
    websocket.send(message);
}