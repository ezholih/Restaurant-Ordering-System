<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!empty finishedOrders}">
	<ul>
	<c:forEach items="${finishedOrders}" var="forder">
		<li class="list-group-item">Order Number: ${forder.id} | Time: ${forder.createTime} | Price: ${forder.totalPrice}</li>
	</c:forEach>
	</ul>
</c:if>