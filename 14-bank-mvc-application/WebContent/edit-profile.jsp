<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
<!-- Bootstrap CSS -->
<link
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
  rel="stylesheet">
<style>
body {
  background-color: #f8f9fa;
}

.container {
  background-color: white;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-top: 50px;
}

.form-group label {
  font-weight: bold;
}
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="CustomerController">Home</a>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
      </ul>
    </div>
  </nav>
  <div class="container">
    <h1 class="mb-4 text-center">Edit Profile</h1>
    
    <form action="CustomerController?action=editCustomer" method="post">
      <input type="hidden" name="id" value="${theCustomer.getId()}"/>
      <div class="form-group">
        <label for="firstName">First Name:</label> <input type="text"
          class="form-control" id="firstName" name="firstName" value="${theCustomer.getFirstName()}">
      </div>
      <div class="form-group">
        <label for="lastName">Last Name:</label> <input type="text"
          class="form-control" id="lastName" name="lastName" value="${theCustomer.getLastName()}">
      </div>
      <div class="form-group">
        <label for="email">Email:</label> <input type="email"
          class="form-control" id="email" name="email" value="${theCustomer.getEmail()}">
      </div>
      <div class="form-group">
        <label for="password">New PassWord:</label> <input type="text"
          class="form-control" id="password" name="password" value="${theCustomer.getPassword()}">
      </div>
      <div class="text-center">
    <button type="submit" class="btn btn-primary">Update</button>
    <button type="button" class="btn btn-secondary" onclick="window.history.back();">Back</button>
    </div>
      
    </form>
  </div>

  <!-- Bootstrap JS, Popper.js, and jQuery -->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script
    src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
  <script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>