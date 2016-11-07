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
    <spring:url value="video/welcome.mp4" var="video"/>

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
    <security:authentication property="authorities" var="roles" scope="page" />
    <c:forEach var="role" items="${roles}">
        <c:if test="${role == 'Customer'}">
            <li><a href="/#/orders/open"><spring:message code="orders"/></a></li>
        </c:if>
        <c:if test="${role == 'Driver'}">
            <li><a href="/#/find-order/open"><spring:message code="find_orders"/></a></li>
        </c:if>
        <c:if test="${role == 'Admin'}">
            <li><a href="/#/users"><spring:message code="users"/></a></li>
        </c:if>
        <c:if test="${role == 'Moderator'}">
            <li><a href="/#/feedbacks"><spring:message code="feedbacks"/></a></li>
        </c:if>
    </c:forEach>
    <li><a href="welcome"><spring:message code="home"/></a></li>
</ul>

<!--Tracking box-->
<video autoplay loop>
    <source src="${video}" type="video/mp4">
</video>

<div id="track_container" class="signin centered">
    <h3><spring:message code="track_baggage"/></h3>
    <c:if test="${access_denied != null}">
        <h4><spring:message code="tooltip.access_denied"/></h4>
    </c:if>
    <spring:message code='tooltip.track' var="tooltip"/>
    <spring:message code='track' var="track"/>
    <mvc:form modelAttribute="orderIdDto" action="tracking" method="post" enctype="utf-8">
        <div class="mess">
            <mvc:input path="orderId" type="number" class="user" min="1" max="9000000000000000000" placeholder="${tooltip}" required="required"/>
            <mvc:errors path="orderId"/>
`        </div>
        <input id="track" type="submit" value="${track}">
        <div class="lost">
            <div class="clear"></div>
        </div>
    </mvc:form>
</div>
</body>
</html>