<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Work with users</title>
    <style>
        <%@include file="/resources/style/tmapp.css"%>
    </style>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
    <script>

    </script>
</head>
<body onload="hideActionForms()">
<h1>Page for work with users</h1>
<div id="userMenu">
    <menu>
        <li><input id="newUser" onclick="showUserArea(id)" name="userAction" type="radio"><label for="newUser">Register new user</label></li>
        <li><input id="userInfo" onclick="showUserArea(id)" name="userAction" type="radio"><label for="userInfo">Show user info</label></li>
        <li><input id="delUser" onclick="showUserArea(id)"  name="userAction" type="radio"><label for="delUser">Delete user</label></li>
    </menu>

</div>
<form class="userworkarea" id="newUserForm">
    Registrate new user
    <p>

</form>
<form class="userworkarea" id="userInfoForm">
    Set user name for see his information
    <p><label for="infoUserName">User name </label>
    <input id="infoUserName">
    <input type="submit" value="show">
</form>
<form class="userworkarea" id="delUserForm">
    Set user name for delete him
    <p><label for="delUserName">User name </label><input id="delUserName">
    <input type="submit" value="delete">
</form>
</body>
</html>
