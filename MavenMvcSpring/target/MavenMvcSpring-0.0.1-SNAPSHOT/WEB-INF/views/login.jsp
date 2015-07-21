<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Login Page</title>
</head>

<body>
	<h2 align="center">Welcome to Login Page</h2>
	
		<!-- The action passes the /login request, command name matches the name "loginForm" in 
		the login controller GET method to bind the Login form to the model -->
	
	<form:form action="login" commandName="loginForm" method="post">
		<table align="center">
			<tr>
				<td colspan="2" align="center"><FONT color="red"><form:errors path="userName" /></FONT></td>
			</tr>
			<tr>
				<td class="right"><form:label path="userName">username :</form:label></td>
				<td class="left"><form:input path="userName" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><FONT color="red"><form:errors path="password" /></FONT></td>
			</tr>
			<tr>
				<td class="right"><form:label path="password">password :</form:label></td>
				<td class="left"><form:password path="password" /></td>
			</tr>
			<tr>
				<td class="right"><input type="submit" value="Submit" id="loginButton"/></td>
				<td class="left"> </td>
			</tr>
		</table>
		
	<table align="center">
		<tr>
			<td align="left"> <a href="register">Register</a></td>
		</tr>
	</table>
		
	</form:form>


</body>
</html>