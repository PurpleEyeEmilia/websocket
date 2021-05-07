<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/taglib.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta name="force-rendering" content="webkit">
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
    <script type="text/javascript" charset="UTF-8"
            src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" charset="UTF-8">
        debugger;
        var path = "${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/";
        var userId = '${kid}';
        var interval;

        var websocket;
        if ('websocket' in window) {
            websocket = new WebSocket("ws://" + path + "wsMy?userId=" + userId);
        } else if ('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://" + path + "wsMy?userId=" + userId);
        } else {
            websocket = new SockJS("http://" + path + "wsMy/sockjs?userId=" + userId);
        }

        websocket.onopen = function (event) {
            console.log("websocket: 已连接！");
            console.log(event);
        };

        websocket.onmessage = function (event) {
            debugger;
            var data = JSON.parse(event.data);
            // alert("您收到了一条新消息！");
            // $("#user").append(data.name).append("~~~");
            $("#time").html("当前时间：" + data.name);
            $("#user").append(data.message);
        };

        websocket.onerror = function (event) {
            console.log("WebSocket:发生错误 ");
            console.log(event);
            interval = setInterval(reconnect(), 15000);
        };

        websocket.onclose = function (event) {
            console.log("WebSocket:已关闭");
            interval = setInterval(reconnect(), 15000);
            console.log(event);
        };

        function reconnect() {
            var flag = false;
            if ('websocket' in window) {
                websocket = new WebSocket("ws://" + path + "wsMy?userId=" + userId);
                flag = true;
            } else if ('MozWebSocket' in window) {
                websocket = new MozWebSocket("ws://" + path + "wsMy?userId=" + userId);
                flag = true;
            } else {
                websocket = new SockJS("http://" + path + "wsMy/sockjs?userId=" + userId);
                flag = true;
            }
            if (flag) {
                clearInterval(interval);
            }

        }

        function clearMessage() {
            $("#user").html("");
        }

        function sendMessage() {

            var message = $("#message").val();
            $.post({
                url: "${pageContext.request.contextPath}/user/message",
                data: {message: message, kid: userId},
                success: function (result) {


                },
                error: function (e) {
                    console.log(e);
                }
            });
        }
    </script>
</head>


<body>
<H2>Hello ${user.name}, 欢迎来到聊天室！</H2>
<h3 id="time"></h3>
<h4>您收到消息：</h4>
<br>
<p id="user">

</p>
<br>
<a href="#" onclick="sendMessage();">发送消息给所有人</a>
<a href="#" onclick="clearMessage();">清空消息</a>
<br>
<input id="message" placeholder="请输入发送的消息！" type="text" value="">
</body>