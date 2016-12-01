<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<meta charset="utf-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <spring:url value="css/about.css" var="style"/>
    <spring:url value="img/favicon.png" var="icon"/>

    <spring:url value="img/search.png" var="search_img"/>
    <spring:url value="img/baggage.png" var="baggage_img"/>
    <spring:url value="img/agreement.png" var="agreement_img"/>

    <link rel="shortcut icon" href="${icon}">
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
    <li><a href="about"><spring:message code="about"/></a></li>
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

<div class="about-container">

<h2><spring:message code="about.about_delivery"/></h2></br>

<p><spring:message code="about.delivery_is"/></p></br>

<p><spring:message code="about.company_mission"/></p></br>

<p><spring:message code="about.send"/></p></br>
<div class="icon-cont">
<div class="icon-containers">
<p><spring:message code="about.find"/></p>
<img src="${search_img}" alt="Search" class="icon-1"></br>
</div>
<div class="icon-containers">
<p><spring:message code="about.arrange"/></p>
<img src="${baggage_img}" alt="Baggage" class="icon-2"></br>
</div> 
<div class="icon-containers">
<p><spring:message code="about.receive"/></p>
<img src="${agreement_img}" alt="Agreement" class="icon-3"></br>
</div>
<div class="clear"></div>
</div>
<p><spring:message code="about.customer_service"/></p></br>
	
<p><spring:message code="about.addition"/></p></br>

<p><spring:message code="about.join_team"/></p></br>
</div>

</body>
</html>