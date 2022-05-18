import java.io.IOException; // Import the IOException class
import java.sql.Connection; // Import the Connection class
import java.sql.PreparedStatement; // Import the PreparedStatement class
import java.sql.ResultSet; // Import the ResultSet class

import javax.servlet.ServletException; // Import the ServletException class
import javax.servlet.annotation.WebServlet; // Import the WebServlet annotation
import javax.servlet.http.HttpServlet; // Import the HttpServlet class
import javax.servlet.http.HttpServletRequest; // Import the HttpServletRequest class
import javax.servlet.http.HttpServletResponse; // Import the HttpServletResponse class

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit") // Define the URL pattern
public class Edit extends HttpServlet { // Create the class
	private static final long serialVersionUID = 1L; // Create the serialVersionUID
       
    /**
     * @see HttpServlet#HttpServlet() // Create the constructor
     */
    public Edit() { // Create the constructor
        super(); // Call the superclass constructor
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Create the doPost method
		// TODO Auto-generated method stub
		try {
			Connection conn = Connect.getConnection(); // Create the connection
			int sl_no = Integer.parseInt(request.getParameter("sl_no")); // Get the sl_no
			String invoice_currency = request.getParameter("invoice_currency"); // Get the invoice_currency
			String cust_payment_terms = request.getParameter("cust_payment_terms"); // Get the cust_payment_terms
			
			PreparedStatement ps = null; // Create the prepared statement
			String query = "update grey_goose.winter_internship set invoice_currency=?, cust_payment_terms=? WHERE sl_no=?"; // Create the query
			
			ps = conn.prepareStatement(query);  // Create the prepared statement
			ps.setString(1, invoice_currency); // Set the invoice_currency
			ps.setString(2, cust_payment_terms); // Set the cust_payment_terms
			ps.setInt(3, sl_no); // Set the sl_no
	        System.out.println(ps.toString()); // Print the prepared statement
			int count = ps.executeUpdate(); // Execute the update
			if(count == 1) // If the count is 1
				response.getWriter().write("Success"); // Write success
			else // If the count is not 1
				response.getWriter().write("Fail"); // Write fail
		} catch(Exception e) { // Catch any exceptions
			e.printStackTrace();    // Print the stack trace
			}
    }
}