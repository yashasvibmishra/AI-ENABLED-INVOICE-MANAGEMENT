import java.io.IOException; // Import the IOException class
import java.sql.Connection; // Import the Connection class
import java.sql.PreparedStatement; // Import the PreparedStatement class

import javax.servlet.ServletException; // Import the ServletException class
import javax.servlet.annotation.WebServlet; // Import the WebServlet annotation
import javax.servlet.http.HttpServlet; // Import the HttpServlet class
import javax.servlet.http.HttpServletRequest; // Import the HttpServletRequest class
import javax.servlet.http.HttpServletResponse; // Import the HttpServletResponse class

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete") // Declare the servlet
public class Delete extends HttpServlet { // Declare the class
	private static final long serialVersionUID = 1L; // Declare the serialVersionUID
       
    /**
     * @see HttpServlet#HttpServlet() // Declare the constructor
     */
    public Delete() { // Declare the constructor
        super(); // Call the superclass constructor
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) // Declare the doGet method
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Declare the method
		// TODO Auto-generated method stub
		try { // Try to connect to the database
			int count=0; String finalstr=""; // Declare the count and finalstr variables
			Connection conn = Connect.getConnection(); // Connect to the database
			String sl_no = request.getParameter("selectedIndexes"); // Get the selectedIndexes parameter
			String index[] = sl_no.split(","); // Split the selectedIndexes parameter
			if(sl_no != null) // If the selectedIndexes parameter is not null
			{
			for(String s:index)  // For each string in the index array
			{
				finalstr = finalstr + "," + s; // Add the string to the finalstr variable
			}
			finalstr = finalstr.substring(1); // Remove the first comma
			PreparedStatement ps = conn.prepareStatement("DELETE FROM winter_internship WHERE sl_no IN ("+sl_no+") "); // Prepare the statement
			
			count = ps.executeUpdate(); // Execute the statement
			if(count == 1) // If the count is 1
				response.getWriter().write("Success"); // Write success
			else // If the count is not 1
				response.getWriter().write("Fail"); // Write fail
			}
		} catch(Exception e) { // Catch any exceptions
			e.printStackTrace();     // Print the stack trace
			}
	}
}

