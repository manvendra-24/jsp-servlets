<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<%
		 String user_name = (String) session.getAttribute("user_name");
	%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="Customer">Home</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<a class="nav-link" href="CustomerController?action=listPassbook&user_name=<%= user_name %>">View Passbook</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="CustomerController?action=newTransaction&user_name=<%= user_name %>">New Transaction</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="CustomerController?action=updateCustomer&user_name=<%= user_name %>">Edit Profile</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron text-center">
					<h1 class="display-4">Welcome, <%= user_name %></h1>
					<p class="lead">View your passbook and view your profile here.</p>
					<hr class="my-4">
					<p class="lead">
						<a class="btn btn-success btn-lg" href="CustomerController?action=listPassbook&user_name=<%= user_name %>" role="button">
							View Passbook
						</a> 
						<a class="btn btn-primary btn-lg" href="CustomerController?action=newTransaction&user_name=<%= user_name %>" role="button">
							New Transaction
						</a> 
						<a class="btn btn-info btn-lg" href="CustomerController?action=updateCustomer&user_name=<%= user_name %>" role="button">
							Edit Profile
						</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>