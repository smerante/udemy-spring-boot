<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>
<div class="container">
	<form:form method="post" modelAttribute="todo">
	<form:hidden path="id"/>
	<form:hidden path="user"/>
		<fieldset class="form-group">
			<form:label path="descr">Description</form:label>
			<form:input path="descr" type="text" class="form-control" required="required" />
		<div><font color="red">${errorMessage}</font></div>
		</fieldset>
		<fieldset class="form-group">
			<form:label path="targetDate">Date</form:label>
			<form:input path="targetDate" type="text" class="form-control" required="required" />
		<div><font color="red">${errorMessage}</font></div>
		</fieldset>
		<button type="submit" class="btn btn-success">Add</button>
	</form:form>
</div>
<%@include file="common/footer.jspf" %>