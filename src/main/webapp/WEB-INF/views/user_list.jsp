<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/taglib.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta name="force-rendering" content="webkit">
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" charset="UTF-8">

    </script>
</head>


<body>
<H2>Hello ${user.name}, 欢迎来到消息中！</H2>
<h4>您收到消息：</h4>
<br>
<p id="user">

</p>
<br>
<%--<a href="${pageContext.request.contextPath}/user/list">发送消息给好友</a>--%>
</body>