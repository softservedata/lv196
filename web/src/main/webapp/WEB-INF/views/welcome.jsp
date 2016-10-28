<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <spring:url value="css/welcome.css" var="style"/>
    <spring:url value="js/welcome.js" var="script"/>

    <link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,300,700' rel='stylesheet' type='text/css'>
    <link href="${style}" rel="stylesheet" type="text/css" media="all"/>

    <title>Delivery.com | welcome</title>
</head>
<body>
<ul class="topnav" id="myTopnav">
    <security:authorize access="isAuthenticated()">
        <li><a href="logout">Sign out</a></li>
    </security:authorize>
    <security:authorize access="isAnonymous()">
        <li><a href="registration">Sign up</a></li>
    </security:authorize>
    <security:authorize access="isAnonymous()">
        <li><a href="login">Sign in</a></li>
    </security:authorize>
    <li><a href="#">About</a></li>
    <li><a href="#">Contact</a></li>
    <li><a href="#">News</a></li>
    <security:authentication property="authorities" var="roles" scope="page" />
    <c:forEach var="role" items="${roles}">
        <c:if test="${role == 'Customer'}">
            <li><a href="/#/orders/open">Orders</a></li>
        </c:if>
        <c:if test="${role == 'Driver'}">
            <li><a href="/#/find-order/open">Find Orders</a></li>
        </c:if>
        <c:if test="${role == 'Admin'}">
            <li><a href="/#/users">Users</a></li>
        </c:if>
        <c:if test="${role == 'Moderator'}">
            <li><a href="/#/feedbacks">Feedbacks</a></li>
        </c:if>
    </c:forEach>
    <li><a href="welcome">Home</a></li>
</ul>

<!--Tracking box-->

<div id="track_container" class="signin centered">
    <h3>Track your baggage</h3>
    <c:if test="${msg != null}">
        <h4>${msg}</h4>
    </c:if>
    <mvc:form modelAttribute="orderIdDto" action="tracking" method="post" enctype="utf-8">
        <div class="mess">
            <mvc:input path="orderId" type="number" class="user" min="1" max="9000000000000000000" placeholder="type here your order id" required="required"/>
            <mvc:errors path="orderId"/>
            <span class="mess1"></span>
        </div>
        <input id="track" type="submit" value="Track">
        <div class="lost">
            <div class="clear"></div>
        </div>
    </mvc:form>
</div>
</body>
</html>