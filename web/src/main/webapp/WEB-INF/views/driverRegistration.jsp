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
        <li><a href="registration.jsp">Sign up</a></li>
    </security:authorize>
    <security:authorize access="isAnonymous()">
        <li><a id="signin_btn" href="#">Sign in</a></li>
    </security:authorize>
    <li><a href="#">About</a></li>
    <li><a href="#">Contact</a></li>
    <li><a href="#">News</a></li>
    <li><a href="welcome">Home</a></li>
</ul>

<!--Driver registration box-->

<div id="reg_driver_container" class="signin registr">
    <h3>Registration</h3>
    <c:if test="${msg != null}">
        <h3>${msg}</h3>
    </c:if>
    <h4>Driver registration</h4>
    <mvc:form modelAttribute="driverRegistration" id="driver_form" action="driverRegister" method = "post" enctype="utf-8">
        <div class="mess">
            <mvc:input path="driverEmail" type="email" name="driverEmail" id="dr_email" class="user" placeholder="Email" maxlength="255"
                   required="required" title="Email should looks like example@domain.com"/>
            <mvc:errors path="driverEmail"/>
            <span class="mess1"></span>
        </div>
        <div class="mess">
            <mvc:input path="driverFirstName" type="text" name="driverFirstName" class="user" id="dr_firstName" pattern=".{3,15}"
                   placeholder="First name" required="required" title="Should will be from 3 to 15 chars"/>
            <mvc:errors path="driverFirstName"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="driverLastName" type="text" class="user" id="dr_lastName" pattern=".{3,15}" name="driverLastName" placeholder="Last name"
                   required="required" title="Should will be from 3 to 15 chars"/>
            <mvc:errors path="driverLastName"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="driverPassword" type="password" name="driverPassword" id="dr_password" pattern=".{4,20}" class="lock"
                   placeholder="Password" required="required" title="Password should will be from 4 to 20 symbols"/>
            <mvc:errors path="driverPassword"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="driverConfirmPassword" type="password" name="driverConfirmPassword" id="dr_confirmPassword" pattern=".{4,20}" class="lock"
                   placeholder="Retype userPassword" required="required" title="Password should will be from 4 to 20 symbols"/>
            <mvc:errors path="driverConfirmPassword"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="driverPhoneNumber" type="text" class="user" id="dr_phoneNumber" pattern=".\d+" name="driverPhoneNumber"
                   placeholder="Phone number" required="required" title="Only digits"/>
            <mvc:errors path="driverPhoneNumber"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="driverPassport" type="text" class="user" id="dr_passport" pattern=".{8, 30}" name="driverPassport" placeholder="Passport"
                   required="required" title="Passport should be from 8 to 30 symbols"/>
            <mvc:errors path="driverPassport"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="driverPhotoUrl" type="text" class="user" id="dr_photoUrl" pattern=".{1, 255}" name="driverPhotoUrl"
                   placeholder="Your photo url" required="required" title="Max length 255 symbols"/>
            <mvc:errors path="driverPhotoUrl"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="vehicleName" type="text" class="user" id="vehicleName" pattern=".{1, 255}" name="vehicleName"
                   placeholder="Car model" required="required" title="Max length 255 symbols"/>
            <mvc:errors path="vehicleName"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="vehicleNumber" type="text" class="user" id="vehicleNumber" pattern=".{1, 255}" name="vehicleNumber"
                   placeholder="Car number" required="required" title="Max length 255 symbols"/>
            <mvc:errors path="vehicleNumber"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="vehicleVIN" type="text" class="user" id="vehicleVIN" pattern=".{17, 17}" name="vehicleVIN"
                   placeholder="Car ID number (17 length)" required="required" title="Length of VIN should be 17 symbols"/>
            <mvc:errors path="vehicleVIN"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="vehicleFrontPhotoURL" type="text" class="user" id="vehicleFrontPhotoURL" pattern=".{1, 255}" name="vehicleFrontPhotoURL"
                   placeholder="Car front photo URL" required="required" title="Max length 255 symbols"/>
            <mvc:errors path="vehicleFrontPhotoURL"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="vehicleBackPhotoURL" type="text" class="user" id="vehicleBackPhotoURL" pattern=".{1, 255}" name="vehicleBackPhotoURL"
                   placeholder="Car back photo URL" required="required" title="Max length 255 symbols"/>
            <mvc:errors path="vehicleBackPhotoURL"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="vehicleWeight" type="number" class="user" min="0.00" max="9.99" step="0.01" id="vehicleWeight" name="vehicleWeight"
                   placeholder="Car's baggage weight" required="required" title="9.99 is max"/>
            <mvc:errors path="vehicleWeight"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="vehicleLength" type="number" class="user" min="0.00" max="9.99" step="0.01" id="vehicleLength" name="vehicleLength"
                   placeholder="Car's baggage length" required="required" title="9.99 is max"/>
            <mvc:errors path="vehicleLength"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="vehicleWidth" type="number" class="user" min="0.00" max="9.99" step="0.01" id="vehicleWidth" name="vehicleWidth"
                   placeholder="Car's baggage width" required="required" title="9.99 is max"/>
            <span class="mess2"></span>
        </div>
        <div class="mess">
            <mvc:input path="vehicleHeight" type="number" class="user" min="0.00" max="9.99" step="0.01" id="vehicleHeight" name="vehicleHeight"
                   placeholder="Car's baggage height" required="required" title="9.99 is max"/>
            <mvc:errors path="vehicleHeight"/>
            <span class="mess2"></span>
        </div>
        <input type="submit" value="register">
        <div class="lost">
            <div class="lost-userPassword">
                <a href="registration">I'm not driver</a>
            </div>
            <div class="clear"></div>
        </div>
    </mvc:form>
</div>
</body>
</html>