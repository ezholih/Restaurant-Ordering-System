<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:if test="${!empty dish}">
			<form:form modelAttribute="dish" action="/ros/roles/addDish" method="post" enctype="multipart/form-data">
		  	<fieldset>		
				<legend>Create New Dish</legend>
				<p>
					<form:label	for="name" path="name" cssErrorClass="error">Dish Name: </form:label><br/>
					<form:input path="name" /> <form:errors path="name" />			
				</p>
				<p>
					<form:label for="price" path="price" cssErrorClass="error">Price: </form:label><br/>
					<form:input path="price" /> <form:errors path="price" />			
				</p>
				<p>
					<form:label for="description" path="description" cssErrorClass="error">Description:</form:label><br/>
					<form:input path="description" /> <form:errors path="description" />			
				</p>
				<p>
					<form:label for="photo" path="photo" cssErrorClass="error">Student Photo:</form:label><br/>
					<form:input path="photo" type="file" /> <form:errors path="photo" />
				<p>	
					<input type="submit" />
				</p>
			</fieldset>
		</form:form>
</c:if>