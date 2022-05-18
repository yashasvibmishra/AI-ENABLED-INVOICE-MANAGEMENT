import java.io.IOException;  // Import the IOException class
import java.sql.Connection; // Import the Connection class
import java.sql.PreparedStatement; // Import the PreparedStatement class
import java.sql.ResultSet; // Import the ResultSet class

import javax.servlet.ServletException; // Import the ServletException class
import javax.servlet.annotation.WebServlet; // Import the WebServlet annotation
import javax.servlet.http.HttpServlet; // Import the HttpServlet class
import javax.servlet.http.HttpServletRequest; // Import the HttpServletRequest class
import javax.servlet.http.HttpServletResponse; // Import the HttpServletResponse class

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add") // Declare the URL pattern
public class Add extends HttpServlet { // Declare the class as a servlet
	private static final long serialVersionUID = 1L; // Declare the serial version UID
       
    /**
     * @see HttpServlet#HttpServlet() // Constructor
     */
    public Add() { // Declare the constructor
        super(); // Call the superclass constructor
        // TODO Auto-generated constructor stub
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Declare the doPost method
		
    	try {
			Connection conn = Connect.getConnection();	// Create a connection to the database
			String business_code = request.getParameter("business_code"); // Get the business code from the form and store it in a variable
			int cust_number = Integer.parseInt(request.getParameter("cust_number")); // Get the customer number from the form and store it in a variable
			String clear_date = request.getParameter("clear_date"); // Get the clear date from the form and store it in a variable
			int buisness_year = Integer.parseInt(request.getParameter("buisness_year")); // Get the business year from the form and store it in a variable
			String doc_id = request.getParameter("doc_id"); // Get the document ID from the form and store it in a variable
			String posting_date = request.getParameter("posting_date"); // Get the posting date from the form and store it in a variable
			String document_create_date = request.getParameter("document_create_date"); // Get the document create date from the form and store it in a variable
			String due_in_date = request.getParameter("due_in_date"); // Get the due in date from the form and store it in a variable
			String invoice_currency = request.getParameter("invoice_currency"); // Get the invoice currency from the form and store it in a variable
			String document_type = request.getParameter("document_type"); // Get the document type from the form and store it in a variable
			int posting_id = Integer.parseInt(request.getParameter("posting_id")); // Get the posting ID from the form and store it in a variable
			double total_open_amount = Double.parseDouble(request.getParameter("total_open_amount")); // Get the total open amount from the form and store it in a variable
			String baseline_create_date = request.getParameter("baseline_create_date"); // Get the baseline create date from the form and store it in a variable
			String cust_payment_terms = request.getParameter("cust_payment_terms"); // Get the customer payment terms from the form and store it in a variable
			int invoice_id = Integer.parseInt(request.getParameter("invoice_id")); // Get the invoice ID from the form and store it in a variable
			
			PreparedStatement ps = null;
			String query = "insert into grey_goose.winter_internship (sl_no, business_code, cust_number, clear_date, buisness_year, doc_id," // Declare the query
					+ "posting_date, document_create_date, due_in_date, invoice_currency, document_type, posting_id," // Declare the query
					+ "total_open_amount, baseline_create_date, cust_payment_terms, invoice_id) " // Declare the query
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; // Declare the query
			
			ps = conn.prepareStatement(query); // Prepare the query
			java.sql.Statement st = conn.createStatement(); // Create a statement
			java.sql.Statement st1 = conn.createStatement(); // Create a statement
			String query1 = "SELECT * FROM customer WHERE cust_number=" + cust_number; // Declare the query
			String query2 = "SELECT COUNT(*) AS total FROM winter_internship"; // Declare the query
			  ResultSet res = st.executeQuery(query1); // Execute the query
			  ResultSet res1 = st1.executeQuery(query2); // Execute the query
			  res1.next(); // Move to the next row
			  
			  int total = res1.getInt("total");   // Get the total
			  total++; // Increment the total
			  ps.setInt(1, total); // Set the first parameter
			  ps.setString(2, business_code); // Set the second parameter
			  if(res.next() == true) { // If the result is true
				  ps.setInt(3, cust_number); // Set the third parameter
			  }// End of if statement
			  else { // Else
		       ps.setNull(3, java.sql.Types.NULL); // Set the third parameter to null
			  } // End of else statement
			ps.setString(4, clear_date); // Set the fourth parameter
			ps.setInt(5, buisness_year); //	Set the fifth parameter
			ps.setString(6, doc_id); // Set the sixth parameter
			ps.setString(7, posting_date); // Set the seventh parameter
			ps.setString(8, document_create_date); // Set the eighth parameter
			ps.setString(9, due_in_date); // Set the ninth parameter
			ps.setString(10, invoice_currency); // Set the tenth parameter
			ps.setString(11, document_type); // Set the eleventh parameter
			ps.setInt(12, posting_id); // Set the twelfth parameter
			ps.setDouble(13, total_open_amount); // Set the thirteenth parameter
			ps.setString(14, baseline_create_date); // Set the fourteenth parameter
			ps.setString(15, cust_payment_terms); // Set the fifteenth parameter
			ps.setInt(16, invoice_id); // Set the sixteenth parameter
	        System.out.println(ps.toString()); // Print the query
			int count = ps.executeUpdate(); // Execute the query
			if(count == 1) //  If the count is 1
				response.getWriter().write("Success"); // Write success
			else // Else
				response.getWriter().write("Fail"); // Write fail
		} catch(Exception e) { // Catch the exception
			e.printStackTrace();    // Print the exception
			}
    }
}
