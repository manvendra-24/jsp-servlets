<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Results</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<table border="1" class="table table-striped table-bordered mt-4">
		<tr>
			<td>Student ID</td>
			<td>First Name</td>
			<td>Last name</td>
			<td>Email</td>
		</tr>
		
		<c:forEach items="${theStudentList}" var="student">
			<tr>
				<td>${student.id}</td>
				<td>${student.firstName}</td>
				<td>${student.lastName }</td>
				<td>${student.email}</td>
				<td><a href="StudentController?command=load&id=${student.id}"
					class=btn-btn-primary>Update</a></td>
				<td><a href="StudentController?command=delete&id=${student.id}"
					class=btn-btn-primary>Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>