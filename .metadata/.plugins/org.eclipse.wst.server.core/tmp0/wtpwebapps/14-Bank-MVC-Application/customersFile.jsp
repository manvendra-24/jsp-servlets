<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer List</title>
  
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Customer List</h1>
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Account Number</th>
                    <th>Balance</th>
                    <th>Update Details</th>
                    <th>Delete Account</th>
                    
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="customer" items="${theCustomerList}">

					<tr>
						
						<td>${customer.getId()}</td>
						<td>${customer.getFirstName() }</td>
						
						<td>${customer.getLastName()}</td>
						<td>${customer.getEmail()}</td>
						<td>${customer.getAccountNumber()}</td>
						<td>${customer.getBalance()}</td>

						

						<td><a href="AdminController?action=editCustomer&Id=${customer.getId()}"
							class="btn btn-secondary">Update</a></td>
							
						<td><a href="AdminController?action=delete&Id=${customer.getId()}"
							class="btn btn-warning">Delete</a></td>
					</tr>

				</c:forEach>
                
            </tbody>
        </table>
        <a href="admin-add-new-Customers.jsp" class="btn btn-primary mt-3">Add New Customer</a>
    </div>

 
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
