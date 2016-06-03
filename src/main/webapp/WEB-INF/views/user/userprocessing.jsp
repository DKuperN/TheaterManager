<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Work with users</title>
    <style>
        <%@include file="/resources/style/tmapp.css"%>
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.js"></script>
   <%--
    &lt;%&ndash;@Deprecated Test script&ndash;%&gt;
    <script type="text/javascript">
        $(document).ready(function() {
            $("#test").click(function(){
                $.get("${pageContext.request.contextPath}/userprocessing/registeruser",function(data,status){
                    alert("Data: " + data + "\nStatus: " + status);
                });
            });
        });
    </script>--%>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
    <script type="text/javascript">
        /*register new user*/
        $(document).ready(function () {
            $('#regNewUser').click(function (event) {
                var userName = $("#newUserName").val();
                var userEmail = $("#newUserEmail").val();
                var jsonObj = {"userName": userName, "userEmail":userEmail};
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/userprocessing/registeruser",
                    data: jsonObj,
//                    contentType: "application/json; charset=utf-8",
//                    dataType: "json",
                    success: function (data) {
                        var result = "New user: " + data + " ..." + data.newUserName + ", " + data.newUserEmail + " added!";
                        var actionResult = $("#actionResultArea");
                        actionResult.css("display", "block");
                        actionResult.text(result);
                    },
                    error: function (e) {
                        alert("Smt wrong ");
                    }
                });
                event.preventDefault();
            })
        });
    </script>
</head>
<body onload="hideActionForms()">
<button id="test">Load</button>
<h1><spring:message code="menu.choseAction"/></h1>
<div id="userMenu">
    <menu>
        <li>
            <input id="newUser" onclick="showUserArea(id)" name="userAction" type="radio">
            <label for="newUser"><spring:message code="menu.title.registerUser"/></label>
        </li>
        <li>
            <input id="userInfo" onclick="showUserArea(id)" name="userAction" type="radio">
            <label for="userInfo"><spring:message code="menu.title.userInfo"/></label>
        </li>
        <li>
            <input id="delUser" onclick="showUserArea(id)"  name="userAction" type="radio">
            <label for="delUser"><spring:message code="menu.title.removeUser"/></label>
        </li>
    </menu>

</div>
<div style="float: left; padding-right: 50px">
    <form class="userworkarea" <%--action="${pageContext.request.contextPath}/userprocessing/registeruser" method="GET" --%>id="newUserForm">
        <spring:message code="menu.title.registerUser"/>
        <p>
        <label for="newUserName"><spring:message code="menu.user.info.Name"/></label><input id="newUserName" name="newUserName">
        <label for="newUserEmail"><spring:message code="menu.user.info.Email"/></label><input id="newUserEmail" name="newUserEmail" type="email">
        <%--<input type="submit" value="<spring:message code="btn.register"/>">--%>
        <input id="regNewUser" type="button" value="<spring:message code="btn.register"/>">
    </form>

    <form class="userworkarea" action="${pageContext.request.contextPath}/userprocessing/userInfo" id="userInfoForm">
        Set user name for see his information
        <p><label for="infoUserName"><spring:message code="menu.user.info"/></label>
        <input id="infoUserName">
        <input type="submit" value="<spring:message code="btn.show"/>">
    </form>

    <form class="userworkarea" action="${pageContext.request.contextPath}/userprocessing/deleteuser" id="delUserForm">
        Set user name for delete him
        <p><label for="delUserName"><spring:message code="menu.user.info"/></label>
        <input id="delUserName">
        <input type="submit" value="<spring:message code="btn.delete"/>">
    </form>
</div>
<div id="actionResultArea"></div>
</body>
</html>
