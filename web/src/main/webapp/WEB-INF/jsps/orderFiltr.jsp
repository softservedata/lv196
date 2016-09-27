<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders</title>
     <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<h2>Active orders to offer</h2>
 <table class="table table-bordered">
 <script src="<c:url value="/bootstrap/js/bootstrap.min.js"/>"></script>

    <thead>
      <tr>
        <th>Id</th>
        <th>Customer name</th>
        <th>City from</th>
        <th>City to</th>
        <th>Weight</th>
        <th>Arrival date</th>
      </tr>
    </thead>

    <tbody>
    <c:forEach var="n" items="${orderList}">
      <tr>
        <td>${n.id}</td>
        <td>${n.customerName}</td>
        <td>${n.cityNameFrom}</td>
        <td>${n.cityNameTo}</td>
        <td>${n.weight}</td>
        <td>${n.arrivalDate}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>
