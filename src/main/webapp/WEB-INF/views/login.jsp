<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false"%>
<html>
<head>
<meta charset="UTF-8">
<title>Navigation Bar</title>
<link rel="stylesheet"
	href="<c:url value='/resources/styles/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/styles/css/bootstrap-theme.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/styles/css/signin.css'/>">
<style type="text/css">
body {
	padding-top: 70px;
}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/component/navbar.jsp" />
	<div class="container">
	    <div class="row">
	        <div class="col-md-4 col-md-offset-4">
	            <div class="panel panel-default">
	                <div class="panel-body">
	                <c:if test="${loginFail == 'fail'}">
	                    <div class="alert alert-warning">
        					<a href="#" class="close" data-dismiss="alert">&times;</a>
        					<strong>Warning!</strong> Wrong user name or password! Try again.
    					</div>
	                </c:if>
	                    <form id="loginForm" class="form-horizontal" role="form" name="f" action="<c:url value='/login' />" method="POST">
	                        <div class="form-group">
	                            <label for="username" class="col-sm-3 control-label">User Name</label>
	                            <div class="col-sm-9">
	                                <input type="text" name="username" class="form-control" id="username" placeholder="USer Name">
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label for="password" class="col-sm-3 control-label">Password</label>
	                            <div class="col-sm-9">
	                                <input type="password" name="password" class="form-control" id="password" placeholder="Password">
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <div class="col-sm-offset-3 col-sm-9">
	                                <div class="checkbox">
	                                    <label class="">
	                                        <input type="checkbox" class="">Remember me</label>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="form-group last">
	                            <div class="col-sm-offset-3 col-sm-9">
	                                <button type="submit" class="btn btn-success btn-sm">Sign in</button>
	                                <button type="reset" class="btn btn-default btn-sm">Reset</button>
	                            </div>
	                        </div>
	                    </form>
	                </div>
	                <div class="panel-footer">Not Registered? <a href="register" class="">Register here</a>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<script
		src="<c:url value='/resources/styles/js/jquery-1.11.2.min.js'/>"></script>
	<script src="<c:url value='/resources/styles/js/jquery.validate.js'/>"></script>
	<script src="<c:url value='/resources/styles/js/bootstrap.min.js'/>"></script>
</body>
</html>
