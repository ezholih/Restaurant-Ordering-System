<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="row">
			<div class="col-md-3">
				<div class="well sidebar-nav">
					<c:import url="/WEB-INF/views/component/menu.jsp" />
				</div>
			</div>

			<div class="col-md-9">
				<div class="jumbotron">
					<a href="register">Register</a>
					<c:import url="/WEB-INF/views/component/banner.jsp" />
				</div>

				<!--/col-->
			</div>
			<!--/row-->
		</div>
		<!--/col-->
	</div>
	<!--/row-->
	<script
		src="<c:url value='/resources/styles/js/jquery-1.11.2.min.js'/>"></script>
	<script src="<c:url value='/resources/styles/js/bootstrap.min.js'/>"></script>

</body>
</html>
