<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Error | Delivery</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <spring:url value="css/error.css" var="style"/>
    <spring:url value="img/favicon.png" var="icon"/>

    <link rel="shortcut icon" href="${icon}">
    <link href='http://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
    <link href="${style}" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="wrap">
        <div class="title">
            <c:if test="${error_code != null}">
                <h1>${error_code}!</h1>
            </c:if>
            <c:if test="${error_msg != null}">
                <h2><spring:message code="${error_msg}"/></h2>
            </c:if>
        </div>
    </div>
    <div class="wrap">
        <div class="gray">
            <a href="welcome" class="ag-3d_button red"><spring:message code="go_home"/></a>
        </div>
    </div>
</body>