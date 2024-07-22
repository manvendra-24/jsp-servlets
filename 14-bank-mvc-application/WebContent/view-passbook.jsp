<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Passbook</title>
<!-- Bootstrap CSS -->
<link
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
  rel="stylesheet">
</head>
<body>
  <%
    String user_name = (String) session.getAttribute("user_name");
  %>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="CustomerController">Home</a>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
      </ul>
    </div>
  </nav>
  <div class="container">
    <form
      action="CustomerController?action=searchTransactions&username=<%=user_name%>"
      method="post" class="search-bar float-right" style="width: 50%;">
      <div class="form-row">
        <div class="col-md-5 mb-3">
          <label for="startDate">From:</label> <input type="date"
            class="form-control" id="startDate" name="startDate" required>
        </div>
        <div class="col-md-5 mb-3">
          <label for="endDate">To:</label> <input type="date"
            class="form-control" id="endDate" name="endDate" required>
        </div>
        <div class="col-md-2 mb-3 d-flex align-items-end">
          <input type="submit" class="btn btn-primary w-100" value="Search">

        </div>
      </div>
    </form>
	</div>
    <div class="container mt-5">
      <h1 class="mb-4">Transaction Details</h1>
      <table class="table table-striped table-bordered">
        <thead class="thead-dark">
          <tr>
            <th>Transaction ID</th>
            <th>Date</th>
            <th>Type</th>
            <th>Detail</th>
            <th>Amount</th>
            <th>New Balance</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="transaction" items="${theTransactionList}">
            <tr>
              <td>${transaction.getId()}</td>

              <td>${transaction.getDate()}</td>
              <td><c:choose>
                  <c:when test="${transaction.getType().equals('credit')}">
                    <span style="color: green;">+${transaction.getType()}</span>
                  </c:when>
                  <c:when test="${transaction.getType().equals('debit')}">
                    <span style="color: red;">-${transaction.getType()}</span>
                  </c:when>
                </c:choose></td>
              
              
              <td><c:if test="${transaction.getType().equals('credit')}">From : ${transaction.getSenderAccNo()}</c:if>
                <c:if test="${transaction.getType().equals('debit')}">To : ${transaction.getReceiverAccNo()}</c:if></td>

              <td><c:choose>
                  <c:when test="${transaction.getType().equals('credit')}">
                    <span style="color: green;">+${transaction.getAmount()}</span>
                  </c:when>
                  <c:when test="${transaction.getType().equals('debit')}">
                    <span style="color: red;">-${transaction.getAmount()}</span>
                  </c:when>
                </c:choose></td>
                
              <td><c:if test="${transaction.getType().equals('credit')}">${transaction.getRbalance()}</c:if>
                <c:if test="${transaction.getType().equals('debit')}">${transaction.getSbalance()}</c:if></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <div class="text-center">
        <button type="button" class="btn btn-secondary mt-3"
          onclick="window.history.back();">Back</button>
      </div>
    </div>
    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script
      src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>