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
				<div class="panel panel-default">
					<div class="panel-heading">Manager Page</div>
					<div class="panel-body">
						<c:choose>
							<c:when test="${empty view}">
								<c:import url="/WEB-INF/views/roles/userHome.jsp" />
							</c:when>
							<c:when test="${view == 'create'}">
								<c:import url="/WEB-INF/views/roles/updateOrCreateMenu.jsp" />
							</c:when>
							<c:when test="${view == 'order' }">
								<c:import url="/WEB-INF/views/roles/viewOrders.jsp" />
							</c:when>
						</c:choose>
					</div>
				</div>
				<!--/col-->
			</div>
			<!--/row-->
		</div>
		<!--/col-->
	</div>
	<!--/row-->
	<script src="<c:url value='/resources/styles/js/jquery-1.11.2.min.js'/>"></script>
	<script src="<c:url value='/resources/ckeditor/ckeditor.js'/>"></script>
	<script src="<c:url value='/resources/ckeditor/adapters/jquery.js'/>"></script>
	<script src="<c:url value='/resources/styles/js/bootstrap.min.js'/>"></script>

</body>
</html>
