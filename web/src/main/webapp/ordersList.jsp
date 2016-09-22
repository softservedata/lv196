<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="get" action="order">
    <button type="submit">getOrder</button>
    <table border="1" cellpadding="8" cellspacing="0">
        <section>
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Order Status</th>
        </tr>
        </thead>
        <c:forEach items="${orderList}" var="order">
            <jsp:useBean id="order" scope="page" class="com.softserve.edu.delivery.domain.Order"/>
            <tr>
                <td><a href="order?action=showorderbyid&id=${order.id}" target="orderInfo">${order.id}</a></td>
                <td>${order.orderStatus}</td>
            </tr>
        </c:forEach>
    </table>
</form>
</section>
</body>
</html>