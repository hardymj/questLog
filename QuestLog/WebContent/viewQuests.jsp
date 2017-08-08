<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
</head>
<body>
<table>
	<tr><th>Title</th><th>Description</th><th>User</th><th>Value</th></tr>
	<c:forEach var="quest" items="${quests}">
	<tr>
		<td>${quest.title}</td>
		<td>${quest.description}</td>
		<td>${quest.user}</td>
		<td>${quest.value}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>