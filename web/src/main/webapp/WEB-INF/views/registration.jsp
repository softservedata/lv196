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
    <spring:url value="video/registration.mp4" var="video"/>

    <link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,300,700' rel='stylesheet' type='text/css'>
    <link href="${style}" rel="stylesheet" type="text/css" media="all"/>

    <title>Delivery.com | Registration</title>
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

<!--Sign up box-->

<div id="reg_container" class="signin centered">
    <h3>Registration</h3>
    <c:if test="${msg != null}">
        <h4>${msg}</h4>
    </c:if>
    <mvc:form modelAttribute="userRegistration" id="register_form" action="register" method = "post" enctype="utf-8">
        <div class="mess">
            <mvc:input path="userEmail" type="email" name="userEmail" id="userEmail" class="user" placeholder="Email" maxlength="255"
                   required="required" title="email should looks like example@domain.com "/>
            <mvc:errors path="userEmail"/>
        </div>
        <div class="mess">
            <mvc:input path="userFirstName" type="text" class="user" id="userFirstName" pattern=".{3,15}" name="userFirstName" placeholder="First name"
                   required="required" title="First name should have from 3 to 15 characters"/>
            <mvc:errors path="userFirstName"/>
        </div>
        <div class="mess">
            <mvc:input path="userLastName" type="text" class="user" id="userLastName" pattern=".{3,15}" name="userLastName" placeholder="Last name"
                   required="required" title="First name should have from 3 to 15 characters"/>
            <mvc:errors path="userLastName"/>
        </div>
        <div class="mess">
            <mvc:input path="userPassword" type="password" name="userPassword" id="userPassword" pattern=".{4,20}" class="lock" placeholder="Password"
                   required="required" title="Password should be from 4 to 20 symbols"/>
            <mvc:errors path="userPassword"/>
        </div>
        <div class="mess">
            <mvc:input path="userConfirmPassword" type="password" name="userConfirmPassword" id="userConfirmPassword"
                       pattern=".{4,20}" class="lock" placeholder="Password again" required="required" title="Password should be from 4 to 20 symbols"/>
            <mvc:errors path="userConfirmPassword"/>
        </div>
        <div class="mess">
            <mvc:input path="userPhoneNumber" type="text" class="user" id="userPhoneNumber" pattern=".\d+" name="userPhoneNumber"
                   placeholder="Phone number" required="required" title="only digits"/>
            <mvc:errors path="userPhoneNumber"/>
        </div>
        <input type="submit" value="register">
        <div class="lost">
            <div class="lost-userPassword">
                <h5>
                    <a href="driverRegistration">I'm driver</a>
                </h5>
            </div>
            <div class="clear"></div>
        </div>
    </mvc:form>
</div>
</body>
</html>