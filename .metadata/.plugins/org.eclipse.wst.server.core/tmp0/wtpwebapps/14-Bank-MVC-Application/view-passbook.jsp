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
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <%
    String user_name = request.getParameter("user_name");
  	
  %>
    <div class="container mt-5">
        <h1 class="mb-4">Passbook</h1>
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Transaction ID</th>
                    <th>Sender Account Number</th>
                    <th>Receiver Account Number</th>
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
            <td>${transaction.getAmount()}</td>
            <td>${transaction.getDate()}</td>
          </tr>
        </c:forEach>
            </tbody>
        </table>
        <a href="CustomerController?action=newTransaction&user_name=<%= user_name %>" class="btn btn-primary mt-3">New Transaction</a>
    </div>

    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>