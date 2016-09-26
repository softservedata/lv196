<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container" id=usersid>
  <h2>Checking users</h2>

</br>
</br>

 <form class="form-inline">
  <div class="form-group">
    <input type="fname" class="form-control" id="lname" placeholder="first name">
  </div>
  <div class="form-group">
    <label for="pwd"></label>
    <input type="lname" class="form-control" id="fname" placeholder="last name">
  </div>
  <div class="form-group">
    <label for="pwd"></label>
    <input type="password" class="form-control" id="email" placeholder="email">
  </div>
  <div class="form-group">
    <label for="pwd"></label>
    <input type="password" class="form-control" id="role" placeholder="role">
  </div>
    <div class="form-group">
    <label for="pwd"></label>
    <input type="password" class="form-control" id="status" placeholder="status">
  </div>

  <button type="submit" class="btn btn-success">Search</button>
</form>

</br>
</br>

  <table class="table">
    <thead>
      <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
        <th>Role</th>
        <th>Status</th>
    <th>Actions</th>
    <th></th>
      </tr>
    </thead>

    <thead>
    <tbody>

    <c:forEach items="${usersList}" var="users">
         <jsp:useBean id="users" scope="page" class="com.softserve.edu.delivery.domain.User"/>
      <tr>
        <td>${users.firstName}</td>
        <td>${users.lastName}</td>
        <td>${users.email}</td>
		<td>${users.userRole.name}</td>
          <td><a  href="users?action=changeStatus&id=${users.email}&status=${!users.blocked}">${users.blocked}</a></td>
        <td><label class="checkbox-inline"><input type="checkbox" value=""></label></td>

      </tr>

      </c:forEach>

      <tr>
        <th> </th>
        <th> </th>
        <th> </th>
        <th> </th>
        <th> </th>
    <th> <button type="button" class="btn btn-danger">Block</button> </th>
    <th> </th>
      </tr>
    </thead>
    </tbody>
  </table>


</body>
</html>