<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Registration Page</title>
</head>

<body>
	<h2 align="center">Welcome to Registration Page</h2>
	<form:form action="register" commandName="registerForm" method="post">
		<table align="center">
			<tr>
				<td colspan="2" align="center"><FONT color="red"><form:errors
							path="userName" /></FONT></td>
			</tr>
			<tr>
				<td class="right"><form:label path="userName">username :</form:label></td>
				<td class="left"><form:input path="userName" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><FONT color="red"><form:errors
							path="password" /></FONT></td>
			</tr>
			<tr>
				<td class="right"><form:label path="password">password :</form:label></td>
				<td class="left"><form:password path="password" /></td>
			</tr>
			<tr>
				<td class="right"><input type="submit" value="Submit"
					/></td>
				<td class="left"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>