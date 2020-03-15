<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>
<div class="container">
	<form method="post" style="text-align:center;">
		<h1>Login</h1>
		<div><font color="red">${errorMessage}</font></div>
		<label style="">Credentials: </label>
		<div><input type="text" name="name" style="width:20%; text-align:center;" placeholder="Username"/></div>
		<div><input type="text" name="password" style="width:20%; text-align:center;" placeholder="password"/></div>
		<div><input type="submit" style="width:10%;"/></div>
	</form>
</div>
<%@include file="common/footer.jspf" %>