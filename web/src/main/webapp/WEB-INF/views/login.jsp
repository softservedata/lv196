<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>

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
                    <li class="">
                        <a href="#">Sign up</a>
                    </li>

                    <li class="">
                        <a href="#">Faq</a>
                    </li>
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
                        <%--<h1>Please sign in</h1>--%>
                       <%-- <a href="#" class="btn btn-custom">Sign up</a>--%>
                        <div class="clearfix"></div>
                    </div>
                </div>

                <div class="col-md-4 col-md-offset-2 col-sm-5 col-sm-offset-1">
                    <div class="home-wrapper">
                        <mvc:form modelAttribute="userAuthDto" id="register_form" action="profile" method="post">
                            <div class="form-topbar"><h3 class="text-center">Sign in</h3></div>
                            <div class="form-group">
                                <mvc:input path="email" type="email" cssClass="form-control" placeholder="email" maxlength="255" required="required"/>
                                <mvc:errors path="email"/>
                            </div>
                            <div class="form-group">
                                <mvc:input path="password" type="password" cssClass="form-control"  placeholder="password" pattern=".{8,20}" title="8 to 20 characters" required="required"/>
                                <mvc:errors path="password"/>
                            </div>
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

    <!-- END FOOTER -->

    <spring:url value="/resources/js/analytics.js.download" var="analytics_js" />
    <spring:url value="/resources/js/bootstrap.min.js.download" var="bootstrap_min_js" />
    <spring:url value="/resources/js/jquery.app.js.download" var="jquery_app_js" />
    <spring:url value="/resources/js/jquery.easing.1.3.min.js.download" var="jquery_easing_js" />
    <spring:url value="/resources/js/jquery.js.download" var="jquery_js_download_js" />
    <spring:url value="/resources/js/jquery.magnific-popup.min.js.download" var="jquery_magnific_js" />
    <spring:url value="/resources/js/jquery.sticky.js.download" var="jquery_sticky_js" />

    <!-- END FOOTER -->
    <!-- js placed at the end of the document so the pages load faster -->
    <script src="${analytics_js}"></script>
    <script src="${bootstrap_min_js}"></script>
    <!-- Jquery easing -->
    <script type="text/javascript" src="${jquery_easing_js}"></script>
    <script type="text/javascript" src="${jquery_magnific_js}"></script>

    <!--sticky header-->
    <script type="text/javascript" src="${jquery_sticky_js}"></script>

    <!--common script for all pages-->
    <script src="${jquery_app_js}"></script>

</body></html>