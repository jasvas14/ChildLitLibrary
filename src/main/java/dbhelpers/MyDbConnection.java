/**
* 
*/
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author jasvas
 *
 */
public class MyDbConnection {

	private static final String dbUrl = "jdbc:mysql://childlit-library.cp9wn3fowyug.us-east-1.rds.amazonaws.com/";
	private static final String dbName = "childlit_library";
	private static final String dbUser = "mist4630";
	private static final String dbPwd = "MgmtInfoSys22!";

	private static Connection connection = null;

	private MyDbConnection() {
	}

	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		}

		String url = dbUrl + dbName;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, dbUser, dbPwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (connection == null) {
			throw new RuntimeException("Error connecting to database.");
		}

		return connection;

	}

}
