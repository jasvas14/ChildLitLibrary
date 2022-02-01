package controllers;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbhelpers.BookDBHelper;

/**
 * Servlet implementation class AuthorServlet
 */
@WebServlet("/author")
public class AuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthorServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the authorName that was passed in through the form.
		String authorName = request.getParameter("authorName");

		// Get the database to use its methods.
		BookDBHelper bdb = new BookDBHelper();

		// Get the results of filtering by the given author's name.
		ResultSet results = bdb.doReadByAuthor(authorName);

		// Get the (not pretty by default) HTML for making a table of books.
		String table = bdb.getHTMLTable(results);

		// Adds a hyperlink before the table to return to the 'read' page.
		table = "<a href=\"/Book_Database_Example\">Home</a>" + table;

		// Set the table as an attribute so that the JSP can access it.
		request.setAttribute("table", table);

		// Pass the execution control to read.jsp along with the table
		// We can technically reuse the read.jsp because it is very simple.
		String url = "/read.jsp";

		// Dispatch the view to the provided URL (the JSP above).
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		// Actually do the dispatch.
		dispatcher.forward(request, response);
	}

}
