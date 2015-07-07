<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:forEach items="${orderlist}" var="order">
<div class="row">
	<c:forEach items="${order.dishes}" var="dish">
	<div class="media">
		<a href="#" class="pull-left">
			<img src="/ros/waiter/dish/${dish.id}/photo" class="media-object"
			alt="${dish.name}" width="300" height="200">
		</a>
		<div class="media-body">
			<h4 class="media-heading">${dish.name}<small><i>Price:
						$ ${dish.price}</i></small>
			</h4>
			<p>${dish.description}</p>
		</div>
	</div>
	</c:forEach>
	<div class="pull-right">
		<h4>Total: ${order.totalPrice}</h4>
	</div>
	<hr/>
</div>
</c:forEach>