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
    <spring:url value="img/linkedin.png" var="linkedIn"/>
    <spring:url value="img/google.png" var="google"/>
    <spring:url value="img/twitter.png" var="twitter"/>
    <spring:url value="img/outlook.png" var="outlook"/>

    <link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,300,700' rel='stylesheet' type='text/css'>
    <link href="${style}" rel="stylesheet" type="text/css" media="all"/>

    <title>Delivery.com | <spring:message code="welcome"/></title>
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

<!--Sign in box-->
<div id="signin_container" class="signin centered">
    <h3><spring:message code="sigin"/></h3>
    
    <c:if test="${wrong_login != null}">
        <h4><spring:message code="tooltip.wrong_login"/></h4>
    </c:if>
    <c:if test="${success_logout != null}">
        <h4><spring:message code="tooltip.success_login"/></h4>
    </c:if>
    <c:if test="${success_register != null}">
        <h4><spring:message code="tooltip.success_register"/></h4>
    </c:if>
    <c:if test="${email_verified != null}">
        <h4><spring:message code="tooltip.email_verified"/></h4>
    </c:if>
    <spring:message code='email' var="email"/>
    <spring:message code='password' var="password"/>
    <spring:message code='tooltip.pasword' var="tooltip_password"/>
    <spring:message code='login' var="login"/>
    <mvc:form modelAttribute="userAuthDto" id="login_form" action="loginProcess" method="post" enctype="utf-8">
        <div class="mess">
            <mvc:input path="email" name="email" type="email"  class="user" placeholder="${email}" maxlength="255"
                       required="required"/>
            <mvc:errors path="email"/>
        </div>
        <div class="mess">
            <mvc:input path="password" type="password" class="lock" placeholder="${password}" pattern=".{4,20}"
                       title="${tooltip_password}" required="required"/>
            <mvc:errors path="password"/>
        </div>
        <input type="submit" value="${login}">
        <div class="lost">
            <div class="lost-userPassword">
                <h5><spring:message code="signin_as"/>:</h5>
                <a href="login/linkedin"><img src="${linkedIn}"></a>
                <a href="login/google"><img src="${google}"></a>
                <a href="#"><img src="${twitter}"></a>
                <a href="#"><img src="${outlook}"></a>
            </div>
            <div class="clear"></div>
        </div>
    </mvc:form>
</div>
</body>
</html>