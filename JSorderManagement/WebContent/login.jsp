<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="imports.jsp" %>
<body background="css/images/mintyellow.jpg">
<div class="container">
	<div class="login-container">

<br>
<form method="post" action="authentication">
<table class="box">
<tr><td colspan="2" align="center">Login</td></tr>
<tr><td>Username:</td><td><input type="text" name="username"></td></tr>
<tr><td>Password:</td><td><input type="password" name="password" align="center"></td></tr>
<tr><td></td><td><input type="submit" name="submit" value="Login"></td></tr>

<tr><td colspan="2" align="center"><font color="red">${errorMsg}</font></td></tr>

<tr><td>

</table>
</form>
</div>
</div>
</body>

<style>

.box {
    width:300px;
    height:300px;   
    position:fixed;
    margin-left:-150px; /* half of width */
    margin-top:-150px;  /* half of height */
    top:50%;
    left:50%;
}

</style>

</html>
