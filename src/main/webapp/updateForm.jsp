<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.Book" %>
    
<% Book book = (Book) request.getAttribute("book"); %>  




<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Children's Literature Library - Update a Book</title>
</head>
<body>

	<h1>Children's Literature Library - Update a Book</h1>
	
	<form name=updateForm action =updateBook method=post>
	
		<label>
			Book ID:
		</label>
   <!--  Changed this field to disabled. Users should not be able to update the ID! -->
   		<input type=text name=bookID value="<%= book.getBookID() %>" readonly="readonly" />
   		<br />
	
		<label>
			Book title:
		</label>
		<input type=text name=title value="<%= book.getTitle() %>" />
		<br />
		
		<label>
			Book author:
		</label>
		<input type=text name=author value="<%= book.getAuthor() %>" />
		<br />
		
		<label>
			Book pages:
		</label>
		<input type=text name=pages value="<%= book.getPages() %>" />
		<br />
		
		<input type=submit name=submit value="Update the Book" />
	
	</form>

</body>
</html>