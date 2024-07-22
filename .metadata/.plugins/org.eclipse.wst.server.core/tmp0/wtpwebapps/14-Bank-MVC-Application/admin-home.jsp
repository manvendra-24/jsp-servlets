<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
	<style>
    body {
        background: url('https://wallpapercave.com/wp/wp10701714.jpg') no-repeat center center fixed;
        background-size: cover;
    }
    .container {
        background-color: rgba(255, 255, 255, 0.8);
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .jumbotron {
        background-color: rgba(255, 255, 255, 0.8);
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Bank Admin</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav ml-auto">

				<li class="nav-item"><a class="nav-link"
					href="admin-add-new-Customers.jsp">Add New Customer</a></li>
				<li class="nav-item"><a class="nav-link"
					href="AdminController?action=listCustomer">View Customers</a></li>
				<li class="nav-item"><a class="nav-link"
					href="AdminController?action=listTransactions">View
						Transactions</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron text-center">
					<h1 class="display-4">Welcome, Admin!</h1>
					<p class="lead">Manage your bank customers and transactions
						from here.</p>
					<hr class="my-4">
					<p class="lead">

						<a class="btn btn-success btn-lg"
							href="admin-add-new-Customers.jsp" role="button">Add New
							Customer</a> <a class="btn btn-primary btn-lg"
							href="AdminController?action=listCustomer" role="button">View
							Customers</a> <a class="btn btn-info btn-lg"
							href="AdminController?action=listTransactions" role="button">View
							Transactions</a>
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
