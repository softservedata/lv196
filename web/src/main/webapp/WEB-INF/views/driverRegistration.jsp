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
    <spring:url value="video/driverRegistr.mp4" var="video"/>

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

<!--Driver registration box-->

<div id="reg_driver_container" class="signin registr">
    <h3>Registration</h3>
    <c:if test="${msg != null}">
        <h4>${msg}</h4>
    </c:if>
    <mvc:form modelAttribute="driverRegistration" id="driver_form" action="driverRegister" method = "post" enctype="utf-8">
        <div class="mess">
            <mvc:input path="driverEmail" type="email" name="driverEmail" id="dr_email" class="user" placeholder="Email" maxlength="255"
                   required="required" title="Email should looks like example@domain.com"/>
            <mvc:errors path="driverEmail"/>
        </div>
        <div class="mess">
            <mvc:input path="driverFirstName" type="text" name="driverFirstName" class="user" id="dr_firstName" pattern=".{3,15}"
                   placeholder="First name" required="required" title="Should will be from 3 to 15 chars"/>
            <mvc:errors path="driverFirstName"/>
        </div>
        <div class="mess">
            <mvc:input path="driverLastName" type="text" class="user" id="dr_lastName" pattern=".{3,15}" name="driverLastName" placeholder="Last name"
                   required="required" title="Should will be from 3 to 15 chars"/>
            <mvc:errors path="driverLastName"/>
        </div>
        <div class="mess">
            <mvc:input path="driverPassword" type="password" name="driverPassword" id="dr_password" pattern=".{4,20}" class="lock"
                   placeholder="Password" required="required" title="Password should will be from 4 to 20 symbols"/>
            <mvc:errors path="driverPassword"/>
        </div>
        <div class="mess">
            <mvc:input path="driverConfirmPassword" type="password" name="driverConfirmPassword" id="dr_confirmPassword" pattern=".{4,20}" class="lock"
                   placeholder="Retype password" required="required" title="Password should will be from 4 to 20 symbols"/>
            <mvc:errors path="driverConfirmPassword"/>
        </div>
        <div class="mess">
            <mvc:input path="driverPhoneNumber" type="text" class="user" id="dr_phoneNumber" pattern=".\d+" name="driverPhoneNumber"
                   placeholder="Phone number" required="required" title="Only digits"/>
            <mvc:errors path="driverPhoneNumber"/>
        </div>
        <div class="mess">
            <mvc:input path="vehicleName" type="text" class="user" id="vehicleName" pattern=".{1, 255}" name="vehicleName"
                   placeholder="Car model" required="required" title="Max length 255 symbols"/>
            <mvc:errors path="vehicleName"/>
        </div>
        <div class="mess">
            <mvc:input path="vehicleNumber" type="text" class="user" id="vehicleNumber" pattern=".{1, 255}" name="vehicleNumber"
                   placeholder="Car number" required="required" title="Max length 255 symbols"/>
            <mvc:errors path="vehicleNumber"/>
        </div>
        <div class="mess">
            <mvc:input path="vehicleVIN" type="text" class="user" id="vehicleVIN" pattern=".{1, 17}" name="vehicleVIN"
                   placeholder="Car ID number" required="required" title="Length of VIN should be from 1 to 17 symbols"/>
            <mvc:errors path="vehicleVIN"/>
        </div>
        <input type="submit" value="register">
        <div class="lost">
            <div class="lost-userPassword">
                <h5>
                    <a href="registration">I'm not driver</a>
                </h5>
            </div>
            <div class="clear"></div>
        </div>
    </mvc:form>
</div>
</body>
</html>