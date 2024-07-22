<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Student Details</title>
	<link
 		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
 		rel="stylesheet"
 		integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
 		crossorigin="anonymous">
	
</head>

<body>
 <h1 class="text-center">student Details</h1>
 <div class="lead">
  		First Name is : 
  		<%= request.getParameter("first_name") %>
 </div>
 <div class="lead">
		  Last Name is :
		  <%= request.getParameter("last_name")%>
 </div>

 <div class="lead">
		  City Name :
		  <%= request.getParameter("city")%>
 </div>

 <div class="lead">
		  Gender :
		  <%= request.getParameter("gender")%>
 </div>

 <div class="lead">
  Languages Known :
  
  <%
 
		String[] languages = request.getParameterValues("languages");
  		if(languages != null){
  			String lang = String.join(", ", languages);
  			out.println(lang);
  		}
  		else{
  			out.println("None");
  		}
	%>
 </div>

</body>
</html>