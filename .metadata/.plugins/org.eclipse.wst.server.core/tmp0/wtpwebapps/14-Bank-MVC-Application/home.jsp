<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank Home Page</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
        background: url('https://img.freepik.com/premium-photo/financial-consulting-commodities-trading-financial-security-screen_593195-2238.jpg?w=1060') no-repeat center center fixed;
        background-size: cover;
        height: 100vh;
    }
    .card {
        background-color: rgba(255, 255, 255, 0.9);
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    .container {
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .input-box {
        margin-bottom: 15px;
    }
    .btn {
        width: 100%;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="card">
            <h2 class="text-center mb-4">Bank MVC Application</h2>
            <form action="UserLoginController" method="get">
                <div class="form-group">
                    <label for="user_type">Login As</label>
                    <select class="form-control" id="user_type" name="user_type">
                        <option value="customer">Customer</option>
                        <option value="admin">Admin</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="text" id="user_name" name="user_name" class="form-control" placeholder="Username" required>
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                </div>
                <button class="btn btn-primary" type="submit" id="login">Login</button>
            </form>
        </div>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
