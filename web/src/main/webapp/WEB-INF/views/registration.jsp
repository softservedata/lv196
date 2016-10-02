<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta charset="UTF-8">
    <title>Delivery - Registration</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

</head>

<body>
<div class="container">
    <br/>
    <h2 style="text-align: center"> User registration form</h2>
    <br/>
    <mvc:form modelAttribute="userRegistration" id="register_form" action="register" method = "post" class="form-horizontal" enctype="utf-8">
        <div class="form-group">
            <label for="email" class="col-sm-2 col-sm-offset-2 control-lable">Email:</label>
            <div class="col-sm-5 ">
                <mvc:input path="email" type="email" class="form-control" id="email" name="email" placeholder="example@domain.com" maxlength="255" required="required"/>
                <mvc:errors path="email"/>
            </div>
        </div>
        <div class="form-group">
            <label for="firstName" class="col-sm-2 col-sm-offset-2 control-lable">First Name:</label>
            <div class="col-sm-5 ">
                <mvc:input path="firstName" type="text" class="form-control" id="firstName" pattern=".{3,15}" name="firstName" placeholder="Your first name" required="required"/>
                <mvc:errors path="firstName"/>
            </div>
        </div>
        <div class="form-group">
            <label for="lastName" class="col-sm-2 col-sm-offset-2 control-lable">Last Name:</label>
            <div class="col-sm-5 ">
                <mvc:input path="lastName" type="text" class="form-control" id="lastName" pattern=".{3,15}" name="lastName" placeholder="Your last name" required="required"/>
                <mvc:errors path="lastName"/>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 col-sm-offset-2 control-lable">Password:</label>
            <div class="col-sm-5 ">
                <mvc:input path="password" type="password" class="form-control" id="password" pattern=".{4,20}" name="password" placeholder="Password" required="required"/>
                <mvc:errors path="password"/>
            </div>
        </div>
        <div class="form-group">
            <label for="confirmPassword" class="col-sm-2 col-sm-offset-2 control-lable">Confirm password:</label>
            <div class="col-sm-5 ">
                <mvc:input path="confirmPassword" type="password" class="form-control" id="confirmPassword" pattern=".{4,20}" name="confirmPassword" placeholder="Confirm password" required="required"/>
                <mvc:errors path="confirmPassword"/>
            </div>
        </div>
        <div class="form-group">
            <label for="phoneNumber" class="col-sm-2 col-sm-offset-2 control-lable">Phone number:</label>
            <div class="col-sm-5 ">
                <mvc:input path="phoneNumber" type="text" class="form-control" id="phoneNumber" pattern=".\d+" name="phoneNumber" title="only digits" placeholder="380501234567" required="required"/>
                <mvc:errors path="confirmPassword"/>
            </div>
        </div>
        <div class="form-group">
            <label for="passport" class="col-sm-2 col-sm-offset-2 control-lable">Passport:</label>
            <div class="col-sm-5 ">
                <mvc:input path="passport" type="text" class="form-control" id="passport" pattern=".{1,30}" name="passport" placeholder="CE12345" required="required"/>
                <mvc:errors path="confirmPassword"/>
            </div>
        </div>
        <div class="form-group">
            <label for="photoUrl" class="col-sm-2 col-sm-offset-2 control-lable">Photo:</label>
            <div class="col-sm-5 ">
                <mvc:input path="photoUrl" type="text" class="form-control" id="photoUrl" pattern=".{1,500}" name="photoUrl" placeholder="Your photo url" required="required"/>
                <mvc:errors path="confirmPassword"/>
            </div>
        </div>

        <div class="col-sm-offset-8">
            <input type ="submit" value = "register" class="btn btn-primary btn-md">
        </div>
    </mvc:form>
</div>
</body>

</html>
