<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ORDER INFO</title>
</head>
<body>

<h2>Order Information</h2>
    <jsp:useBean id="order"  scope="request" type="com.softserve.edu.delivery.domain.Order"/>
    <jsp:useBean id="feedback" scope="request" class="com.softserve.edu.delivery.domain.Feedback"/>
    <table rules="rows" border="0" cellpadding="8" cellspacing="0">
    <tr>
        <td>Status</td>
        <td>Description</td>
        <td>Transporter</td>
        <td>Feeedback</td>
    </tr>
    <tr>
        <td>${order.orderStatus}</td>
        <td>${order.description}</td>
        <td>${order.customer.firstName} ${order.customer.lastName}</td>
        <td>${feedback.text}</td>
        <td>${feedback.rate}</td>
    </tr>
    </table>

<h2>Offer for Order</h2>
    <table rules="rows" border="0" cellpadding="8" cellspacing="0">
        <tr>
            <td>Offer ID</td>
            <td>Approved</td>
            <td>Car Photo</td>
        </tr>
    <c:forEach items="${offerList}"  var="offers">
    <jsp:useBean id="offers" scope="page" class="com.softserve.edu.delivery.domain.Offer"/>
         <tr>
             <td>${offers.offerId}</td>
             <td><a href="order?action=changeOfferStatus&id=${offers.offerId}&status=${offers.approved}">
                     ${offers.approved}</a></td>
             <td><img height="150" src="${offers.car.vehicleFrontPhotoURL}"></td>
         </tr>
    </c:forEach>
    </table>
</body>
</html>