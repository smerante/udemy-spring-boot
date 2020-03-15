<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>
<div class="container">
	<form method="post" style="text-align:center;">
		<h1>Welcome ${name}</h1>
		<a href="list-todos">Click here to manage Todos</a>
		<hr/>
	</form>
</div>
<%@include file="common/footer.jspf" %>