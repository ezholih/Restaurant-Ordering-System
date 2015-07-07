<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${!empty dishlist}">
	<form:form modelAttribute="fdOrder" action="/ros/roles/createOrder"
			method="post">
		<c:forEach items="${dishlist}" var="dish" varStatus="dishCounter">
			<div class="media">
				<a href="/ros/roles/waiter/${dish.id}/adddish" class="pull-left">
					<img src="/ros/waiter/dish/${dish.id}/photo" class="media-object"
					alt="${dish.name}" width="810" height="540">
				</a>
				<div class="media-body">
					<h4 class="media-heading">${dish.name}<small><i>Price:
								$ ${dish.price}</i></small>
					</h4>
					<p>${dish.description}</p>
					<form:checkbox path="dishId" value="${dish.id}"/>
				</div>
			</div>
		</c:forEach>
		<form:label for="tbNumber" path="tbNumber" cssErrorClass="error">Table Number:</form:label><br />
		<form:input path="tbNumber" /> <form:errors path="tbNumber" />
		<input type="submit" value="Create" class="btn btn-primary">
	</form:form>
</c:if>
