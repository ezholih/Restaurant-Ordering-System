<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="<c:url value='/resources/styles/js/jquery-1.11.2.min.js'/>"></script>
<script src="<c:url value='/resources/ckeditor/ckeditor.js'/>"></script>
<script src="<c:url value='/resources/ckeditor/adapters/jquery.js'/>"></script>
<c:if test="${!empty menu}">
	<div class="row">
		<div class="col-md-9">
			<form:form modelAttribute="menu"
				action="/ros/roles/updateOrCreateMenu" method="post" class="form-inline" role="form">
				<fieldset>
					<legend>Create Menu</legend>
					<div class="form-group">
						<form:label for="Description" path="name" cssErrorClass="error" class="sr-only">Description:</form:label>
						<form:input path="name" class="form-control" placeholder="Description"/>
						<form:errors path="name" />
					</div>
					<input type="submit" class="btn btn-primary pull-right" />
				</fieldset>
			</form:form>
		</div>
	</div>
</c:if>
<div class="row">
	<c:if test="${!empty menulist}">
		<div class="col-md-9">
		<h4>Menu List</h4>
		<table class="table table-striped">
			<c:forEach items="${menulist}" var="menu">
				<tr>
					<td>${menu.name}</td>
					<td><a href="/ros/roles/${menu.id}/addDish"
						class="btn btn-primary pull-right">Add Dish</a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</c:if>
</div>
<div class="row">
<div class="col-md-9">
	<c:if test="${!empty dish}">
		<form:form modelAttribute="dish"
			action="/ros/roles/${menu.id}/addDish" method="post"
			enctype="multipart/form-data">
			<fieldset>
				<legend>Create New Dish</legend>
				<div class="form-group">
					<form:label for="name" path="name" cssErrorClass="error" class="sr-only">Dish Name: </form:label>
					<form:input path="name" class="form-control" placeholder="name"/>
					<form:errors path="name" />
				</div>
				<div class="form-group">
					<form:label for="price" path="price" cssErrorClass="error" class="sr-only">Price: </form:label>
					<form:input path="price" class="form-control" placeholder="price"/>
					<form:errors path="price" />
				</div>
				<div class="form-group">
					<form:label for="description" path="description"
						cssErrorClass="error"  class="sr-only">Description:</form:label>
					<form:textarea path="description" class="form-control ckeditor" placeholder="description"/>
					<form:errors path="description" />
				</div>
				<div class="form-group">
					<form:label for="photo" path="photo" cssErrorClass="error" class="sr-only">Student Photo:</form:label>
					<form:input path="photo" type="file"  class="form-control"/>
					<form:errors path="photo" />
				</div>
					<input type="submit" class="btn btn-success pull-right" />
			</fieldset>
		</form:form>
	</c:if>
	</div>
</div>
<div class="row">
<div class="col-md-9">
	<c:if test="${!empty dishList}">
		<c:forEach items="${dishList}" var="dish">
			<table class="table table-hover">
				<tr>
					<td class="img-thumbnail"><h3>${dish.name}</h3>
					<img
						src="/ros/manager/dish/${dish.id}/photo" class="img-responsive"
						alt=""></td>
				</tr>
			</table>
		</c:forEach>
	</c:if>
	</div>
</div>
<script type="text/javascript">
	$( 'textarea.editor' ).ckeditor();
</script>