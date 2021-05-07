<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta name="force-rendering" content="webkit">
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
    <script type="text/javascript" charset="UTF-8" src="js/jquery/jquery-1.9.1.js"></script>
    <%--<script type="text/javascript" charset="UTF-8">
        debugger;
        var path = "${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/";
        var userId = '4971030049329152';

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
            console.log("websocket: 已接受到一条消息！" + data);
            // alert("websocket: 已接受到一条消息！");
            // document.getElementById("user").innerText += data.username + "~~~";
            $("#user").append(data.username).append("~~~");
        };

        websocket.onerror = function (event) {
            console.log("WebSocket:发生错误 ");
            console.log(event);
        };

        websocket.onclose = function (event) {
            console.log("WebSocket:已关闭");
            console.log(event);
        };
    </script>--%>
    <script type="text/javascript" charset="UTF-8">
        function login() {
            var kid = $("#kid").val();
            window.location.href = "${pageContext.request.contextPath}/user/login?kid=" + kid;
        }
    </script>
</head>


<body>
<H2>Hello World!</H2>
<p id="user">

</p>
<br>
<form id="userForm">
    <input id="kid" type="text" name="kid">
    <input id="button" type="button" onclick="login();" value="登录">
</form>
</body>