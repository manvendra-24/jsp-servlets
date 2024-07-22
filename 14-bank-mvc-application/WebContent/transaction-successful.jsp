<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction Successful</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="CustomerController">Home</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
			</ul>
		</div>
	</nav>
    <div class="container mt-5">
        <div class="card">
            <div class="card-body">
                <h1 class="card-title">Transaction Successful</h1>
                <p class="card-text">Transaction completed without any failure</p>
                <a href="CustomerController?action=home" class="btn btn-primary">Back to Home</a>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>