<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./template/header.jsp"/>
<header id="head" class="secondary"></header>

<!-- container -->
<div class="container">

    <ol class="breadcrumb">
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li class="active">Registration</li>
    </ol>

    <div class="row">

        <!-- Article main content -->
        <article class="col-xs-12 maincontent">
            <header class="page-header">
                <h1 class="page-title">Registration</h1>
            </header>

            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3 class="thin text-center">Register a new account</h3>
                        <p class="text-center text-muted">Lorem ipsum dolor sit amet, <a href="<c:url value='/login'/>">Login</a>
                            adipisicing elit. Quo nulla quibusdam cum doloremque incidunt nemo sunt a tenetur omnis
                            odio. </p>
                        <hr>

                        <form:form method="POST" modelAttribute="user" class="form-horizontal">

                            <div class="top-margin">
                                <label class="col-md-3 control-lable" for="login">Login<span
                                        class="text-danger">*</span></label>
                                <form:input type="text" path="login" id="login" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="login" class="help-inline"/>
                                </div>
                            </div>

                            <div class="top-margin">
                                <label class="col-md-3 control-lable" for="password">Password<span
                                        class="text-danger">*</span></label>
                                <form:input type="password" path="password" id="password"
                                            class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="password" class="help-inline"/>
                                </div>
                            </div>

                            <div class="top-margin">
                                <label class="col-md-3 control-lable" for="email">Email<span
                                        class="text-danger">*</span></label>
                                <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="email" class="help-inline"/>
                                </div>
                            </div>

                            <div class="top-margin">
                                <label class="col-md-3 control-lable" for="firstName">First Name<span
                                        class="text-danger">*</span></label>
                                <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="firstName" class="help-inline"/>
                                </div>
                            </div>

                            <div class="top-margin">
                                <label class="col-md-3 control-lable" for="lastName">Last Name<span class="text-danger">*</span></label>
                                <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="lastName" class="help-inline"/>
                                </div>
                            </div>

                            <div class="top-margin">
                                <label class="col-md-4 control-lable" for="userProfiles">Roles</label>
                                <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id"
                                             itemLabel="type" class="form-control"/>
                                <div class="has-error">
                                    <form:errors path="userProfiles" class="help-inline"/>
                                </div>
                            </div>

                            <hr>

                            <div class="row">
                                <div class="col-lg-8">
                                    <label class="checkbox">
                                        <input type="checkbox">
                                        I've read the <a href="#">Terms and Conditions</a>
                                    </label>
                                </div>
                                <div class="col-lg-4 text-right">
                                    <button class="btn btn-action" value="Register" type="submit">Register</button>
                                </div>
                            </div>
                        </form:form>


                    </div>
                </div>

            </div>

        </article>
        <!-- /Article -->

    </div>
</div>
<!-- /container -->
<jsp:include page="./template/footer.jsp"/>
