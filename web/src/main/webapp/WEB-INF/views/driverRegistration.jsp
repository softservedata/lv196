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

    <title>Delivery.com | <spring:message code="registration"/></title>
</head>
<body>
<ul class="topnav" id="myTopnav">
    <li><a href="?mylocale=en">EN</a>|<a href="?mylocale=uk">UA</a></li>
    <security:authorize access="isAuthenticated()">
        <li><a href="logout"><spring:message code="signout"/></a></li>
    </security:authorize>
    <security:authorize access="isAnonymous()">
        <li><a href="registration"><spring:message code="signup"/></a></li>
    </security:authorize>
    <security:authorize access="isAnonymous()">
        <li><a href="login"><spring:message code="sigin"/></a></li>
    </security:authorize>
    <li><a href="#"><spring:message code="about"/></a></li>
    <li><a href="#"><spring:message code="contact"/></a></li>
    <li><a href="#"><spring:message code="news"/></a></li>
    <li><a href="welcome"><spring:message code="home"/></a></li>
</ul>

<video autoplay loop>
    <source src="${video}" type="video/mp4">
</video>

<!--Driver registration box-->

<div id="reg_driver_container" class="signin registr">
    <h3><spring:message code="registration"/></h3>
    <spring:message code='tooltip.wrong_register' var="wrong_register"/>
    <spring:message code='tooltip.success_register' var="success_register"/>
    <c:if test="${wrong_register != null}">
        <h4>${success_register}</h4>
    </c:if>
    <spring:message code='register' var="register"/>
        <spring:message code='email' var="email"/>
    	<spring:message code='password' var="password"/>
    	<spring:message code='password_again' var="password_again"/>
	    <spring:message code='fname' var="fname"/>
	    <spring:message code='lname' var="lname"/>
    	<spring:message code='phone' var="phone"/>
    	<spring:message code='car_model' var="car_model"/>
	    <spring:message code='car_number' var="car_number"/>
	    <spring:message code='car_id' var="car_id"/>
    	<spring:message code='tooltip.email' var="tooltip1"/>
	    <spring:message code='tooltip.fname' var="tooltip2"/>
	    <spring:message code='tooltip.lname' var="tooltip3"/>
	    <spring:message code='tooltip.password2' var="tooltip4"/>
	    <spring:message code='tooltip.phone' var="tooltip5"/>
		<spring:message code='tooltip.car' var="tooltip6"/>
		<spring:message code='tooltip.carid' var="tooltip7"/>
    <mvc:form modelAttribute="driverRegistration" id="driver_form" action="driverRegister" method = "post" enctype="utf-8">
        <div class="mess">
            <mvc:input path="driverEmail" type="email" name="driverEmail" id="dr_email" class="user" placeholder="${email}" maxlength="255"
                   required="required" title="${tooltip1}"/>
            <mvc:errors path="driverEmail"/>
        </div>
        <div class="mess">
            <mvc:input path="driverFirstName" type="text" name="driverFirstName" class="user" id="dr_firstName" pattern=".{3,15}"
                   placeholder="${fname}" required="required" title="${tooltip2}"/>
            <mvc:errors path="driverFirstName"/>
        </div>
        <div class="mess">
            <mvc:input path="driverLastName" type="text" class="user" id="dr_lastName" pattern=".{3,15}" name="driverLastName" placeholder="${lname}"
                   required="required" title="${tooltip3}"/>
            <mvc:errors path="driverLastName"/>
        </div>
        <div class="mess">
            <mvc:input path="driverPassword" type="password" name="driverPassword" id="dr_password" pattern=".{4,20}" class="lock"
                   placeholder="${password}" required="required" title="${tooltip4}"/>
            <mvc:errors path="driverPassword"/>
        </div>
        <div class="mess">
            <mvc:input path="driverConfirmPassword" type="password" name="driverConfirmPassword" id="dr_confirmPassword" pattern=".{4,20}" class="lock"
                   placeholder="${password_again}" required="required" title="${tooltip4}"/>
            <mvc:errors path="driverConfirmPassword"/>
        </div>
        <div class="mess">
            <mvc:input path="driverPhoneNumber" type="text" class="user" id="dr_phoneNumber" pattern=".\d+" name="driverPhoneNumber"
                   placeholder="${phone}" required="required" title="${tooltip5}"/>
            <mvc:errors path="driverPhoneNumber"/>
        </div>
        <div class="mess">
            <mvc:input path="vehicleName" type="text" class="user" id="vehicleName" pattern=".{1, 255}" name="vehicleName"
                   placeholder="${car_model}" required="required" title="${tooltip6}"/>
            <mvc:errors path="vehicleName"/>
        </div>
        <div class="mess">
            <mvc:input path="vehicleNumber" type="text" class="user" id="vehicleNumber" pattern=".{1, 255}" name="vehicleNumber"
                   placeholder="${car_number}" required="required" title="${tooltip6}"/>
            <mvc:errors path="vehicleNumber"/>
        </div>
        <div class="mess">
            <mvc:input path="vehicleVIN" type="text" class="user" id="vehicleVIN" pattern=".{1, 17}" name="vehicleVIN"
                   placeholder="${car_id}" required="required" title="${tooltip7}"/>
            <mvc:errors path="vehicleVIN"/>
        </div>
        <input type="submit" value="${register}">
        <div class="lost">
            <div class="lost-userPassword">
                <h5>
                    <a href="registration"><spring:message code="notdriver"/></a>
                </h5>
            </div>
            <div class="clear"></div>
        </div>
    </mvc:form>
</div>
</body>
</html>