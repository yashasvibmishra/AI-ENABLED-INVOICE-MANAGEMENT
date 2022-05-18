import java.sql.*; //  Import the Connection, PreparedStatement, ResultSet, and SQLException classes

public class Connect  // Declare the class
{
	static final String jdbc_driver = "com.mysql.cj.jdbc.Driver"; // Declare the JDBC driver
	static final String url = "jdbc:mysql://localhost:3306/grey_goose"; // Declare the URL
	static final String user = "root"; // Declare the user
	static final String pass = "root"; // Declare the password
	
	private static java.sql.Connection conn; // Declare the connection
	
	public static Connection getConnection() throws Exception // Declare the getConnection method
	{
		try { // Try to connect to the database
			Class.forName(jdbc_driver); // Load the JDBC driver
			conn = DriverManager.getConnection(url, user, pass); // Connect to the database
			return conn; // Return the connection
		} catch(Exception e) { // Catch any exceptions
			e.printStackTrace(); // Print the stack trace
		}
		return null; // Return null
	}
}
