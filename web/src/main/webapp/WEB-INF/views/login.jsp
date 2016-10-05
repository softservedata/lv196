<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">

    <spring:url value="/resources/img/Delivery-icon.ico" var="icon"/>
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrap_css"/>
    <spring:url value="/resources/css/css" var="css"/>
    <spring:url value="/resources/css/css(1)" var="css1"/>
    <spring:url value="/resources/css/magnific-popup.css" var="magnific_popup_css"/>
    <spring:url value="/resources/css/materialdesignicons.min.css" var="materialDesign"/>
    <spring:url value="/resources/css/style.css" var="style"/>

    <!-- Icon -->
    <link rel="shortcut icon" href="${icon}">

    <!-- Title -->
    <title>Delivery - Sign in</title>

    <!-- Google fonts -->
    <link rel="stylesheet" href="${css}">
    <link rel="stylesheet" href="${css1}">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${bootstrap_css}" >

    <!-- Magnific-popup -->
    <link rel="stylesheet" href="${magnific_popup_css}">

    <!-- Icon-font -->
    <link rel="stylesheet" href="${materialDesign}">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="${style}">

</head>


<body data-spy="scroll" data-target="#data-scroll-menu">

    <!-- Pre-loader -->
    <div class="preloader" style="display: none;">
        <div class="status" style="display: none;">&nbsp;</div>
    </div>


    <!-- Navbar -->
    <div id="undefined-sticky-wrapper" class="sticky-wrapper" style="height: 76px;"><div class="navbar navbar-custom sticky" role="navigation">
        <div class="container">

            <!-- Navbar-header -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <i class="mdi mdi-menu"></i>
                </button>
                <!-- LOGO -->
                <a class="navbar-brand logo" href="${icon}">
                    <span>delivery.com</span>
                </a>
            </div>
            <!-- end navbar-header -->

            <!-- menu -->
            <div class="navbar-collapse collapse" id="data-scroll-menu">
                <ul class="nav navbar-nav navbar-right">
                    <li class="">
                        <a href="welcome">Home</a>
                    </li>
                    <security:authorize access="isAnonymous()">
                        <li class="">
                            <a href="registration">Sign up</a>
                        </li>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
                        <li class="">
                            <a href="">You are: ${userPrincipal}</a>
                        </li>
                        <li class="">
                            <a href="logout">Sign out</a>
                        </li>
                    </security:authorize>
                </ul>
            </div>
            <!--/Menu -->
        </div>
        <!-- end container -->
    </div></div>
    <!-- end navbar -->



    <!-- HOME -->
    <section class="home bg-img-4 home-intro" id="home">
        <div class="bg-overlay"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-6 text-left col-sm-6">
                    <div class="home-wrapper">
                        <div class="clearfix"></div>
                    </div>
                </div>

                <div class="col-md-4 col-md-offset-2 col-sm-5 col-sm-offset-1">
                    <div class="home-wrapper">
                        <mvc:form modelAttribute="userAuthDto" id="login_form" action="loginProcess" method="post">
                            <div class="form-topbar"><h3 class="text-center">Sign in</h3></div>
                            <div class="form-group">
                                <mvc:input path="email" name="email" type="email" cssClass="form-control" placeholder="email" maxlength="255" required="required"/>
                                <mvc:errors path="email"/>
                            </div>
                            <div class="form-group">
                                <mvc:input path="password" name="password" type="password" cssClass="form-control"  placeholder="password" pattern=".{4,20}" title="4 to 20 characters" required="required"/>
                                <mvc:errors path="password"/>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-secondary">Continue</button>
                            </div>
                        </mvc:form>
                    </div>
                </div>

            </div>
        </div>
    </section>
    <!-- END HOME -->

    <!-- FOOTER -->
    <footer class="bg-gray footer">
        <div class="container">

            <div class="row">
                <div class="col-sm-12">
                    <div class="text-center">
                        <a class="logo" href="#">
                            <span>delivery.com</span>
                        </a>
                        <ul class="list-inline menu-list m-t-30">
                            <li><a href="#"> About Us</a></li>
                            <li><a href="#"> Help &amp; Support</a></li>
                            <li><a href="#"> Terms &amp; Conditions</a></li>
                            <li><a href="#"> Privacy Policy</a></li>
                        </ul>

                        <p class="text-muted m-b-0"> © delivery.com 2016 - All Right Reserved</p>

                   </div>
                </div>
            </div>
            <!-- end row -->
        </div>
    </footer>
</body></html>