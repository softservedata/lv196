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

<!--Sign up box-->

<div id="reg_container" class="signin centered">
    <h3><spring:message code="registration"/></h3>

    <c:if test="${wrong_register != null}">
        <h4><spring:message code="tooltip.wrong_register"/></h4>
    </c:if>
    <c:if test="${success_register != null}">
        <h4><spring:message code="tooltip.success_register"/></h4>
    </c:if>
    <spring:message code='email' var="email"/>
    <spring:message code='password' var="password"/>
    <spring:message code='password_again' var="password_again"/>
    <spring:message code='fname' var="fname"/>
    <spring:message code='lname' var="lname"/>
    <spring:message code='phone' var="phone"/>
    <spring:message code='tooltip.email' var="tooltip1"/>
    <spring:message code='tooltip.fname' var="tooltip2"/>
    <spring:message code='tooltip.lname' var="tooltip3"/>
    <spring:message code='tooltip.password2' var="tooltip4"/>
    <spring:message code='tooltip.phone' var="tooltip5"/>
    <spring:message code='register' var="register"/>
    <mvc:form modelAttribute="userRegistration" id="register_form" action="register" method = "post" enctype="utf-8">
        <div class="mess">
            <mvc:input path="userEmail" type="email" name="userEmail" id="userEmail" class="user" placeholder="${email}" maxlength="255"
                   required="required" title="${tooltip1}"/>
            <mvc:errors path="userEmail"/>
        </div>
        <div class="mess">
            <mvc:input path="userFirstName" type="text" class="user" id="userFirstName" pattern=".{3,15}" name="userFirstName" placeholder="${fname}"
                   required="required" title="${tooltip2}"/>
            <mvc:errors path="userFirstName"/>
        </div>
        <div class="mess">
            <mvc:input path="userLastName" type="text" class="user" id="userLastName" pattern=".{3,15}" name="userLastName" placeholder="${lname}"
                   required="required" title="${tooltip3}"/>
            <mvc:errors path="userLastName"/>
        </div>
        <div class="mess">
            <mvc:input path="userPassword" type="password" name="userPassword" id="userPassword" pattern=".{4,20}" class="lock" placeholder="${password}"
                   required="required" title="${tooltip4}"/>
            <mvc:errors path="userPassword"/>
        </div>
        <div class="mess">
            <mvc:input path="userConfirmPassword" type="password" name="userConfirmPassword" id="userConfirmPassword"
                       pattern=".{4,20}" class="lock" placeholder="${password_again}" required="required" title="${tooltip4}"/>
            <mvc:errors path="userConfirmPassword"/>
        </div>
        <div class="mess">
            <mvc:input path="userPhoneNumber" type="text" class="user" id="userPhoneNumber" pattern=".\d+" name="userPhoneNumber"
                   placeholder="${phone}" required="required" title="${tooltip5}"/>
            <mvc:errors path="userPhoneNumber"/>
        </div>
        <input type="submit" value="${register}">
        <div class="lost">
            <div class="lost-userPassword">
                <h5>
                    <a href="driverRegistration"><spring:message code="idriver"/></a>
                </h5>
            </div>
            <div class="clear"></div>
        </div>
    </mvc:form>
</div>
</body>
</html>