<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<div class="container">
		<div class="text-center">
			<p class="h2">Add Student</p>
		</div>
		<hr>
		<form action="StudentController" method="get">
			<input type="hidden" name="command" value="add"/>
	
	        <label for="first_name">First Name:</label><br>
	        <input type="text" id="first_name" name="first_name" required><br><br>
	        
	        <label for="last_name">Last Name:</label><br>
	        <input type="text" id="last_name" name="last_name" required><br><br>
	        
	        <label for="email">Email:</label><br>
	        <input type="email" id="email" name="email" required><br><br>
	        
	        <button type="submit" value="Submit">Submit</button>
    	</form>
	</div>
    
</body>
</html>