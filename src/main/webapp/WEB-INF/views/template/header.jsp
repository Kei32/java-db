<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="com.kei.cisco.service.AuthUserService" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">
	
	<title>${title}</title>

	<link rel="shortcut icon" href="<c:url value='/static/images/gt_favicon.png'/>">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/static/css/font-awesome.min.css'/>">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="<c:url value='/static/css/bootstrap-theme.css'/>" media="screen" >
	<link rel="stylesheet" href="<c:url value='/static/css/main.css'/>">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="<c:url value='/static/js/html5shiv.js'/>"></script>
	<script src="<c:url value='/static/js/respond.min.js'/>"></script>
	<![endif]-->
</head>

<body class="home">
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="<c:url value='/'/>"><img src="<c:url value='/static/images/logo.png'/>" alt="Progressus HTML5 template"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="active"><a href="<c:url value='/'/>">Home</a></li>
					<%
						String user = AuthUserService.getUserName();
						if(AuthUserService.isAutorized()) {
					%>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user-circle-o fa-lg"></i> <%= user %> </b> <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value='/lk'/>">LK</a></li>
							<li><a href="<c:url value='/lk/settings'/>">Settings</a></li>
							<li><a href="<c:url value='/workspace'/>">WorkSpace</a></li>
							<li class="active"><a class="btn" href="<c:url value='/logout'/>">Logout</a></li>
						</ul>
					</li>
					<% } else { %>
					<li><a class="btn" href="<c:url value='/login'/>">SIGN IN / SIGN UP</a></li>
					<% } %>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div> 
	<!-- /.navbar -->

