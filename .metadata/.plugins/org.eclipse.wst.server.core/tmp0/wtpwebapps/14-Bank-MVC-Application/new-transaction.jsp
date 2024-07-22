<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New Transaction</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 600px;
            margin-top: 50px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .btn {
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="mb-4">New Transaction</h2>
    <form id="transactionForm" action="CustomerController?action=addTransaction" method="post">
        <div class="form-group">
            <label for="senderAccountNumber">Sender Account Number:</label>
            <input type="hidden" class="form-control" id="senderAccountNumber" name="senderAccountNumber" value="<%= request.getAttribute("senderAccountNumber") != null ? request.getAttribute("senderAccountNumber") : "" %>">
            <input type="text" class="form-control" id="displaySenderAccountNumber" value="<%= request.getAttribute("senderAccountNumber") != null ? request.getAttribute("senderAccountNumber") : "" %>" disabled>
        </div>
        <div class="form-group">
            <label for="receiverAccountNumber">Receiver Account Number:</label>
            <input type="text" class="form-control" id="receiverAccountNumber" name="receiverAccountNumber" required>
        </div>
        <div class="form-group">
            <label for="amount">Amount:</label>
            <input type="number" class="form-control" id="amount" name="amount" required>
        </div>
        <button type="submit" class="btn btn-primary">Send</button>
    </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
