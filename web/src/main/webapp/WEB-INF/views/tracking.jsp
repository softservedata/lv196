<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<title>Delivery - Order information</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<spring:url value="/resources/css/style1.css" var="style"/>
	<spring:url value="/resources/css/jquery-ui.css" var="jquery_ui"/>
	<spring:url value="/resources/img/Delivery-icon.ico" var="icon"/>

	<link rel="shortcut icon" href="${icon}">
	<link rel="stylesheet" href="${style}"/>
	<link rel="stylesheet" href="${jquery_ui}"/>
</head>
<body>
	<div class="main">
		<h1>Order information</h1>
		<div class="agileinfo_main">
			<form id="testform" action="welcome" method="post" novalidate>

				<fieldset style="border: 1px solid #999">
					<legend>From :</legend>
					<p>City</p>
					<a target="_blank" href="https://www.google.com.ua/maps/place/${order.cityFrom}">
					<input id="cityFrom" type="text" value="${order.cityFrom}" disabled></a>
					<p>Costumer</p>
					<input id="costumerName" type="text" value="${order.customerName}" disabled>
				</fieldset>

				<fieldset style="border: 1px solid #999">
					<legend>To :</legend>
					<p>City</p>
					<a target="_blank" href="https://www.google.com.ua/maps/place/${order.cityTo}">
					<input id="cityTo" type="text" value="${order.cityTo}" disabled/></a>
					<p>Transporter</p>
					<input id="transporterName" type="text" value="${order.transporterName}" disabled/>
					<p>Receiver</p>
					<input id="receiverName" type="text" value="${order.receiverName}" disabled/>
				</fieldset>

				<fieldset style="border: 1px solid #999">
					<legend>Order :</legend>
					<p>Status</p>
					<input id="orderStatus" type="text" value="${order.orderStatus}" disabled>
					<p>Last location</p>
					<a target="_blank" href="https://www.google.com.ua/maps/place/${order.lastLocation}">
					<input target="_blank" id="lastLocation" type="text" value="${order.lastLocation}" disabled></a>
					<p>Visited</p>
					<input id="lastTime" type="text" value="${order.lastTimeVisited}" disabled>
					<p>Expexted arrival time</p>
					<input id="arrival" type="text" value="${order.expectedArrivalTime}" disabled>
				</fieldset>

				<fieldset style="border: 1px solid #999">
					<legend>Baggage :</legend>
					<p>Height</p>
					<input id="height" type="text" value="${order.height}" disabled>
					<p>Width</p>
					<input id="width" type="text" value="${order.width}" disabled>
					<p>Length</p>
					<input id="length" type="text" value="${order.length}" disabled>
					<p>Weight</p>
					<input id="weight" type="text" value="${order.weight}" disabled>
				</fieldset>

				<input type="submit" value="Back">
			</form>
		</div>
		<div class="agileits_copyright">
			<p>© 2016 Delivery All rights reserved</p>
		</div>
	</div>
</body>
</html>