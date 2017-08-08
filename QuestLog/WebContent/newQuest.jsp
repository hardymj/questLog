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
	<h1>Quest</h1>
	<div class="quest">
	<form id="quest" action="FormSubmit" method="post">
		
		<div><label for="title">Title</label><input name="title" type="text"></input></div>
		<div><label for="description">Description</label><input name="description" type="text"></input></div>
		<div><label for="user">User</label><input name="user" type="text"></input></div>
		<div><label for="value">Value</label><input name="value" type="text"></input></div>
		<input type="submit" name="action" value="Submit"/>	
	</form>
	</div>
</body>
</html>