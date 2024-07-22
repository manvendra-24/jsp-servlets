<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>To do list</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container mt-3">
		<h1 class="text-center"> To do List</h1><hr>
        <form action="jsp-session-todos.jsp" method="post">
            <input type="text" name="task" placeholder="Enter a new task" required>
            <button type="submit">Add</button>
        </form>
        <hr>
		<%
			String task = request.getParameter("task");
	        HttpSession session1 = request.getSession();
	        List<String> tasks = (List<String>) session1.getAttribute("tasks");
	        if (tasks == null) {
	            tasks = new ArrayList<>();
	            session1.setAttribute("tasks", tasks);
	        }
	
	        tasks.add(task);
    		for(String t: tasks){
    			out.println(t + "\n");
    		}
		%>
	</div>
</body>
</html>