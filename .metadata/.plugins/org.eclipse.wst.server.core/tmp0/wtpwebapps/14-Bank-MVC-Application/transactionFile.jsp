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
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
    </style>
</head>
<body>
    <div class="container">
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
    </div>

    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
