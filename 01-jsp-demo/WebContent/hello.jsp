<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jsp Demo</title>
</head>
<body>
	<H1>hello</H1>
	
	<!--  EXPRESSION TAG -->
	Todays date is <%= new java.util.Date() %><br>
	Sum of 10 and 20 is <%= 10 + 20 %><br>
	Is 10 is greater than 20 <%= 10 > 20 %><br>
	String in caps <%= new String("hello").toUpperCase() %>
	
	<!-- SCRIPTLET TAGS -->
	<%
		for(int i=1;i<=10;i++){
			out.println("<h4>number "+ i+"</h4>");
		}
	%>
	
	<!-- DECLARATION TAGS -->
	<%!	int square(int n){
			return n*n;
		}
	%>
	<%= square(3) %>
	
	<%! String convertToUpperCase(String str){
			return str.toUpperCase();
		}
	%>
	<%= convertToUpperCase("String") %>
	
	<%!String upperCase(String input) {
  			String[] words = input.split(" ");
  			for (int i = 0; i < words.length; i++) {
   				words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
  			}
  			String output = String.join(" ", words);
  			return output;
 		}
	 %>
 	<%= upperCase("india is my country") %>
	
</body>
</html>