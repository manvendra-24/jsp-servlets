<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page
 import="java.util.List,java.util.ArrayList,com.techlabs.model.Student"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Details</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Student ID</td>
        <td>First Name</td>
        <td>Last name</td>
        <td>Email</td>
    </tr>
    <c:forEach var="student" items="${theStudentList}">
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName }</td>
            <td>${student.email}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>