<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ORDER INFO</title>
</head>
<body>
<section>
    <table>

            <jsp:useBean id="oneOrder" scope="request" type="com.softserve.edu.delivery.domain.Order"/>
            <tr>
                <td>Status</td>
                <td>Description</td>
                <td>Offers</td>
            </tr>
            <tr>
                <td>${oneOrder.orderStatus}</td>
                <td>${oneOrder.description}</td>
                <td>${oneOrder.offers}</td>
            </tr>
    </table>
</section>
</body>
</html>