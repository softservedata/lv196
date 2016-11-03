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
    <spring:url value="video/login.mp4" var="video"/>

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
    <li><a href="welcome">Home</a></li>
</ul>

<video autoplay loop>
    <source src="${video}" type="video/mp4">
</video>

<!--Sign in box-->
<div id="signin_container" class="signin centered">
    <h3>Sign in</h3>
    <c:if test="${msg != null}">
        <h4>${msg}</h4>
    </c:if>
    <mvc:form modelAttribute="userAuthDto" id="login_form" action="loginProcess" method="post" enctype="utf-8">
        <div class="mess">
            <mvc:input path="email" name="email" type="email"  class="user" placeholder="Email" maxlength="255"
                       required="required"/>
            <mvc:errors path="email"/>
        </div>
        <div class="mess">
            <mvc:input path="password" type="password" class="lock" placeholder="Password" pattern=".{4,20}"
                       title="From 4 to 20 characters" required="required"/>
            <mvc:errors path="password"/>
        </div>
        <input type="submit" value="Log in">
        <div class="lost">
            <div class="lost-userPassword">
                <h5>
                    <a href="registration">I don't have an account</a>
                </h5>
            </div>
            <div class="clear"></div>
        </div>
    </mvc:form>
</div>
</body>
</html>