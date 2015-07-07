<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="profile-sidebar">
<!-- SIDEBAR USERPIC -->
				<div class="profile-userpic">
					<img src="/ros/manager/${user.id}/photo" class="img-responsive" alt="">
				</div>
				<!-- END SIDEBAR USERPIC -->
				<!-- SIDEBAR USER TITLE -->
				<div class="profile-usertitle">
					<div class="profile-usertitle-name">
						${user.firstName} ${user.lastName}
					</div>
					<div class="profile-usertitle-job">
						${user.role}
					</div>
				</div>
				<!-- END SIDEBAR USER TITLE -->
				<!-- SIDEBAR BUTTONS -->
				<div class="profile-userbuttons">
					<a href="<c:url value="/logout" />"><button type="button" class="btn btn-danger btn-sm">Logout</button></a>
				</div>
				
				<div class="profile-usermenu">
					<ul class="nav">
						<li>
							<a href="/ros/roles/${user.id}/updateUserInfo">
							<i class="glyphicon glyphicon-link"></i>
							User Info</a>
						</li>
						<li>
							<a href="/ros/roles/updateOrCreateMenu">
							<i class="glyphicon glyphicon-link"></i>
							Manage Menu</a>
							</li>
						<li>
							<a href="/ros/roles/viewOrders">
							<i class="glyphicon glyphicon-link"></i>
							View Orders</a>
						</li>

					</ul>
				</div>
</div>