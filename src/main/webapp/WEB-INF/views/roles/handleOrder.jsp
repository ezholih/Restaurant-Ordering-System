<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:forEach items="${unfinishedOrders}" var="order">
	<div class="row">
	<div class="col-md-12">
		<form action="/ros/roles/handleOrder/${order.id}" method=post>
			<c:forEach items="${order.dishes}" var="dish">
				<div class="form-group">
					<div class="media">
						<a href="#" class="pull-left"> <img
							src="/ros/waiter/dish/${dish.id}/photo" class="thumbnail"
							alt="${dish.name}" width="300" height="200">
						</a>
						<div class="media-body">
							<h3 class="media-heading">${dish.name}</h3>
							<p>${dish.description}</p>
						</div>
					</div>
				</div>
			</c:forEach>
			<button type="submit" class="btn btn-primary">Finished</button>
		</form>
	</div>
	</div>
</c:forEach>
<hr/>
<div class="row">
	<c:if test="${!empty finishedOrders}">
	<div class="col-md-12">
	<h4>Finished Orders: </h4>
		<ul>
			<c:forEach items="${finishedOrders}" var="forder">
				<li class="list-group-item">Order Number: ${forder.id} |
					${forder.createTime}</li>
			</c:forEach>
		</ul>
		</div>
	</c:if>
</div>