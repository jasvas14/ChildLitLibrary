<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String table = (String) request.getAttribute("table");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Children's Literature Library</title>
</head>
<body>

	<h1>Children's Literature Library</h1>
	
	<%=  table %>
	
	<a href="addForm.html"> Add a Book </a>

</body>
</html>