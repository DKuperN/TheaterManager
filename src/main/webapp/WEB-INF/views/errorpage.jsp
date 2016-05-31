<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Not ready</title>
    <%--<jsp:include page="/resources/style/tmapp.css"/>--%>
    <style>
        <%@include file="/resources/style/tmapp.css"%>
    </style>
</head>
<body>
<div id="ErrorPage">
    <h1>Page not ready yet :((</h1>
    <div id="errorMessage">${errmessage}</div>
    <div>${sttrace}</div>
</div>
</body>
</html>
