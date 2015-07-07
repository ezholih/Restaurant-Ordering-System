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
	href="<c:url value='/resources/styles/css/sidebar.css'/>">
<style type="text/css">
body {
	padding-top: 70px;
}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/component/navbar.jsp" />
	<div class="container">
		<div class="col-md-4 col-md-offset-4">
			<form:form modelAttribute="user" action="/ros/roles/updateUserInfo" method="put" enctype="multipart/form-data">
			<legend>Register New User</legend>
				<div class="form-group">
					<form:label	for="userName" path="userName" cssErrorClass="error" class="sr-only">User Name:</form:label><br/>
					<form:input path="userName" class="form-control" placeholder="User Name"/> <form:errors path="userName" />			
				</div>
				<div class="form-group">
					<form:label for="password" path="password" cssErrorClass="error" class="sr-only">Password:</form:label><br/>
					<form:password path="password"  class="form-control" placeholder="Password"/> <form:errors path="password"/>			
				</div>
				<div class="form-group">
					<form:label for="role" path="role" cssErrorClass="error" class="sr-only">First Name:</form:label><br/>
					<form:select path="role"  class="form-control">
						<form:option value="ROLE_MANAGER" />
						<form:option value="ROLE_WAITER" />
						<form:option value="ROLE_CHEF" />
					</form:select>
					<form:errors path="role" />			
				</div>
				<div class="form-group">
					<form:label	for="firstName" path="firstName" cssErrorClass="error" class="sr-only">First Name:</form:label><br/>
					<form:input path="firstName" class="form-control" placeholder="First Name"/> <form:errors path="firstName" />			
				</div>
				<div class="form-group">	
					<form:label for="lastName" path="lastName" cssErrorClass="error" class="sr-only">Last Name:</form:label><br/>
					<form:input path="lastName" class="form-control" placeholder="Last Name"/> <form:errors path="lastName" />
				</div>
				<div class="form-group">	
					<form:label for="photo" path="photo" cssErrorClass="error" class="sr-only">Student Photo:</form:label><br/>
					<form:input path="photo" type="file" class="form-control"/> <form:errors path="photo" />
				</div>	
					<input type="submit" class="btn btn-primary"/>
		</form:form>
		</div>
	</div>

	<script src="<c:url value='/resources/styles/js/bootstrap.min.js'/>"></script>
	<script
		src="<c:url value='/resources/styles/js/jquery-1.11.2.min.js'/>"></script>
</body>
</html>
