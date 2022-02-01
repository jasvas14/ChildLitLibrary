/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Book;

/**
 * @author jasvas A class for methods to work with our Book database.
 */
public class BookDBHelper {

	private Connection connection;

	public BookDBHelper() {
		connection = MyDbConnection.getConnection();
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet doReadAll() {

		// Create a query to select all of the books
		String query = "SELECT bookID, title, author, pages FROM books";

		ResultSet results = null;

		try {
			// Set up a preparedstatement to hold and implement the query
			PreparedStatement ps = this.connection.prepareStatement(query);

			// Implement the query and get the results
			results = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Return the resultset

		return results;
	}

	public ResultSet doReadByAuthor(String name) {
		// Create a query to select all of the books

		String query = "SELECT bookID, title, author, pages " + "FROM books " + "WHERE author='" + name + "'";
		ResultSet results = null;
		try {
			// Set up a preparedstatement to hold and implement the query
			PreparedStatement ps = this.connection.prepareStatement(query);
			// Implement the query and get the results
			results = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Return the resultset

		return results;
	}

	// Return a String that holds the contents of the resultset as a displayable
	// html table
	public String getHTMLTable(ResultSet results) {

		String table = "";
		table += "<table border=1>\n";

		try {
			while (results.next()) {

				Book book = new Book(results.getInt("bookID"), results.getString("title"), results.getString("author"),
						results.getInt("pages"));

				table += "<tr>";
				table += "\t<td>";
				table += book.getTitle();
				table += "</td>";
				table += "<td>";

				table += "<form action=\"author\" method=\"post\">";
				table += "<input type=\"hidden\" name=\"authorName\" value=\"" + book.getAuthor() + "\">";
				table += "<input type=\"submit\" value=\"" + book.getAuthor() + "\">";
				table += "</form>";
				table += "</td>";

				table += "<td>";
				table += book.getPages();
				table += "</td>";
				table += "\n\t<td>";

				table += "<form action=\"update\" method=\"post\">";
				table += "<input type=\"hidden\" name=\"bookID\" value=\"" + book.getBookID() + "\">";
				table += "<input type=\"submit\" value=\"Update\">";
				table += "</form>";

				table += "<form action=\"delete\" method=\"post\">";
				table += "<input type=\"hidden\" name=\"bookID\" value=\"" + book.getBookID() + "\">";
				table += "<input type=\"submit\" value=\"Delete\">";
				table += "</form>";

				table += "</td>\n";

				table += "</tr>\n";

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		table += "</table>";
		return table;
	}

	public void doDelete(int bookID) {
		String query = "DELETE FROM books WHERE bookID = ?";

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, bookID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doAdd(Book book) {
		String query = "INSERT INTO books (title, author, pages) values (?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getPages());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Book doReadOne(int bookID) {
		String query = "SELECT * FROM books WHERE bookID = ?";

		Book book = null;

		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, bookID);
			ResultSet results = ps.executeQuery();

			results.next();

			// What if book isn't found? Is an exception thrown?
			// Is it okay to return null from this refactored method?

			book = new Book(results.getInt("bookID"), results.getString("title"), results.getString("author"),
					results.getInt("pages"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	public void doUpdate(Book book) {
		String query = "UPDATE books SET title=?, author=?, pages=? WHERE bookID=?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getPages());
			ps.setInt(4, book.getBookID());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
