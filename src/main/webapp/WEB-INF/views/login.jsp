<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="./template/header.jsp"/>
<header id="head" class="secondary"></header>

<!-- container -->
<div class="container">

	<ol class="breadcrumb">
		<li><a href="<c:url value='/'/>">Home</a></li>
		<li class="active">User access</li>
	</ol>

	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">Sign in</h1>
			</header>

			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="panel panel-default">
					<div class="panel-body">
						<h3 class="thin text-center">Sign in to your account</h3>
						<p class="text-center text-muted">Lorem ipsum dolor sit amet, <a href="<c:url value='/signup' />">Register</a> adipisicing elit. Quo nulla quibusdam cum doloremque incidunt nemo sunt a tenetur omnis odio. </p>
						<hr>

						<div class="login-form">
							<c:url var="loginUrl" value="/login" />
							<form action="${loginUrl}" method="post" class="form-horizontal">
								<c:if test="${param.error != null}">
									<div class="alert alert-danger">
										<p>Invalid username and password.</p>
									</div>
								</c:if>
								<c:if test="${param.logout != null}">
									<div class="alert alert-success">
										<p>You have been logged out successfully.</p>
									</div>
								</c:if>
								<c:if test="${success != null}">
									<div class="alert alert-success">
										<p>${success}</p>
									</div>
								</c:if>
								<div class="input-group input-sm">
									<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
									<input type="text" class="form-control" id="username" name="login" placeholder="Enter Username" required>
								</div>
								<div class="input-group input-sm">
									<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
									<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									   value="${_csrf.token}" />

								<hr>

								<div class="row">
									<div class="col-lg-8">
										<b><a href="#">Forgot password?</a></b>
									</div>
									<div class="col-lg-4 text-right">
										<button class="btn btn-action" type="submit">Sign in</button>
									</div>
								</div>
							</form>
						</div>

					</div>
				</div>

			</div>

		</article>
		<!-- /Article -->
	</div>
</div>	<!-- /container -->
<jsp:include page="./template/footer.jsp"/>
