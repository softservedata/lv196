<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<title>Delivery - User profile</title>
<!-- for-mobile-apps -->
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
		<h1>Profile</h1>
		<div class="agileinfo_main">
			<form id="testform" action="welcome" method="post" novalidate>

				<fieldset style="border: 1px solid #999">

					<legend>User Details :</legend>

					<p>Email address</p>
					<input id="emailAddress" type="email" value="${userProfile.email}" disabled>

					<p>First name</p>
					<input id="firstName" type="text" value="${userProfile.firstName}" disabled/>

					<p>Last name</p>
					<input id="lastName" type="text" value="${userProfile.lastName}" disabled/>
					
					<p>Phone number</p>
					<input id="phoneNum" name="phoneNum" type="text" value="${userProfile.phoneNumber}" disabled/>

					<p>Passport</p>
					<input id="passport" name="passport" type="text" value="${userProfile.passport}" disabled/>

					<p>Role</p>
					<input id="role" name="role" type="text" value="${userProfile.role}" disabled/>

					<p>Approved</p>
					<input id="approved" name="approved" type="text" value="${userProfile.approved}"/>

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