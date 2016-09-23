<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ORDER INFO</title>
</head>
<body>
<h2>Order Information</h2>

    <jsp:useBean id="order" scope="request" type="com.softserve.edu.delivery.domain.Order"/>
    <table rules="rows" border="0" cellpadding="8" cellspacing="0">

    <tr>
    <td>Status</td>
    <td>Description</td>
    <td>Transporter</td>
    <td>Order Id</td>
    </tr>
    <tr>
    <td>${order.orderStatus}</td>
    <td>${order.description}</td>
    <td>${order.customer.email}</td>
    <td>${order.id}</td>
    </tr>
    </table>
<h2>Offer for Order</h2>
    <table rules="rows" border="0" cellpadding="8" cellspacing="0">
        <section>
        <tr>
            <td>Offer ID</td>
            <td>Approved</td>
            <td>Car Photo</td>

        </tr>
        <c:forEach items="${offerList}" var="offer">
        <jsp:useBean id="offer" scope="request" class="com.softserve.edu.delivery.domain.Offer"/>
            <tr>
                <td>${offer.offerId}</td>
                <td>${offer.approved}</td>
                <td><img width="300" src="${offer.car.vehicleFrontPhotoURL}"> </td>
            </tr>
        </c:forEach>
        </section>
    </table>

</body>

</html>