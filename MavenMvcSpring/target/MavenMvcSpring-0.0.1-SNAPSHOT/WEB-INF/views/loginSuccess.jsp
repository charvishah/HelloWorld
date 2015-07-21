<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring3 Annotations MySQL Login Success</title>
</head>
<body>

	<table class="logout">
		<tr>
			<td><a href="login"> Log Out ...</a></td>
		</tr>
		<tr>
			<td class="success">Welcome <b><core:out
						value="${loginForm.userName}" /></b> you have successfully logged in
				to the application ... :)
			</td>
		</tr>
	</table>
	<table>
		<th>User Names</th>
		<th> City </th>
		<core:forEach items="${listOfUsers}" var="current">
			<tr>
				<td><core:out value="${current.username}" /> 
				<td> <core:out value="${current.city}" />
				<td>
				<core:if test="${loginForm.userName ne current.username}">
						<form:form action="delete" method="post">
							<input type="hidden" name="id" value="${current.userid}" />
							<input type="hidden" name="currentUser" value="${loginForm.userName}" />
							<input type="submit" value="Delete" id="delete" class="right" />
						</form:form>
				</core:if>
				</td>

			</tr>

		</core:forEach>
	</table>
</body>
</html>