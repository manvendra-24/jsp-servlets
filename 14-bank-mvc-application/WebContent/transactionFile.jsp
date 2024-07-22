<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction List</title>
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

.table th, .table td {
  vertical-align: middle;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
}

.search-bar {
  margin-top: 20px;
}
</style>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="AdminController">Home</a>

    <div class="collapse navbar-collapse">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
      </ul>
    </div>
  </nav>
  
  <div class="container">
    <form action="AdminController?action=searchTransactions" method="post" class="search-bar float-right" style="width: 50%;">
      <div class="form-row">
        <div class="col-md-5 mb-3">
          <label for="startDate">From:</label>
          <input type="date" class="form-control" id="startDate" name="startDate" required>
        </div>
        <div class="col-md-5 mb-3">
          <label for="endDate">To:</label>
          <input type="date" class="form-control" id="endDate" name="endDate" required>
        </div>
        <div class="col-md-2 mb-3 d-flex align-items-end">
          <input type="submit" class="btn btn-primary w-100" value="Search">
          
        </div>
      </div>
    </form>

    <h1>Transaction List</h1>
    <table class="table table-striped table-bordered">
      <thead class="thead-dark">
        <tr>
          <th>ID</th>
          <th>Sender Account Number</th>
          <th>Receiver Account Number</th>
          <th>Type</th>
          <th>Amount</th>
          <th>Date</th>
        </tr>
      </thead>

      <tbody>
        <c:forEach var="transaction" items="${theTransactionList}">
          <tr>
            <td>${transaction.getId()}</td>
            <td>${transaction.getSenderAccNo() }</td>
            <td>${transaction.getReceiverAccNo()}</td>
            <td>${transaction.getType()}</td>
            <td>${transaction.getAmount()}</td>
            <td>${transaction.getDate()}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <button type="button" class="btn btn-secondary" onclick="window.history.back();">Back To Admin Home</button>
  </div>

  <!-- Bootstrap JS, Popper.js, and jQuery -->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script
    src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
  <script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>