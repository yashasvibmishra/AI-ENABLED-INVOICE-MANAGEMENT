import java.io.IOException; // Import the IOException class
import java.sql.Connection; // Import the Connection class
import java.sql.ResultSet; // Import the ResultSet class
import java.util.ArrayList; // Import the ArrayList class
import java.util.List; // Import the List class

import javax.servlet.ServletException; // Import the ServletException class
import javax.servlet.annotation.WebServlet; // Import the WebServlet annotation
import javax.servlet.http.HttpServlet; // Import the HttpServlet class
import javax.servlet.http.HttpServletRequest; // Import the HttpServletRequest class
import javax.servlet.http.HttpServletResponse; // Import the HttpServletResponse class

import com.google.gson.Gson; // Import the Gson class

/**
 * Servlet implementation class GetUser
 */
@WebServlet("/GetUser") // Define the URL pattern
public class GetUser extends HttpServlet { // Create the class
	private static final long serialVersionUID = 1L; // Create the serialVersionUID
       
    /**
     * @see HttpServlet#HttpServlet() // Create the constructor
     */
    public GetUser() { // Create the constructor
        super(); // Call the superclass constructor
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) // Create the doGet method
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Create the doGet method
		
		try {
		   Connection conn = Connect.getConnection(); // Create the connection
		   java.sql.Statement st = conn.createStatement(); // Create the statement
		   String page = request.getParameter("page") != null ? request.getParameter("page") : "0"; // Get the page
		   String rowsPerPage = request.getParameter("rowsPerPage") != null ? request.getParameter("rowsPerPage") : "50000"; // Get the rowsPerPage
		   int offset = (Integer.parseInt(page)) * (Integer.parseInt(rowsPerPage)); // Calculate the offset
		   String query = "SELECT * FROM winter_internship ORDER BY sl_no ASC limit "+ rowsPerPage + " OFFSET " + offset; // Create the query
		   ResultSet rs = st.executeQuery(query);// Execute the query
		   
		   List <POJO> data = new ArrayList<>(); // Create the list
		   while(rs.next()) { // While the result set has next
		     POJO obj = new POJO(); // Create the POJO
		     obj.setSl_no(rs.getInt("sl_no")); // Set the sl_no
		     obj.setBusiness_code(rs.getString("business_code")); // Set the business_code
		     obj.setCust_number(rs.getInt("cust_number")); // Set the cust_number
		     obj.setClear_date(rs.getString("clear_date")); // Set the clear_date
		     obj.setBuisness_year(rs.getInt("buisness_year")); // Set the buisness_year
		     obj.setDoc_id(rs.getString("doc_id")); // Set the doc_id 
		     obj.setPosting_date(rs.getString("posting_date")); // Set the posting_date
		     obj.setDocument_create_date(rs.getString("document_create_date")); // Set the document_create_date
		     obj.setDocument_create_date1(rs.getString("document_create_date1")); // Set the document_create_date1
		     obj.setDue_in_date(rs.getString("due_in_date")); // Set the due_in_date
		     obj.setInvoice_currency(rs.getString("invoice_currency")); // Set the invoice_currency
		     obj.setDocument_type(rs.getString("document_type")); // Set the document_type
		     obj.setPosting_id(rs.getInt("posting_id")); // Set the posting_id
		     obj.setArea_business(rs.getInt("area_business")); // Set the area_business
		     obj.setTotal_open_amount(rs.getDouble("total_open_amount")); // Set the total_open_amount
		     obj.setBaseline_create_date(rs.getString("baseline_create_date")); // Set the baseline_create_date
		     obj.setCust_payment_terms(rs.getString("cust_payment_terms")); // Set the cust_payment_terms
		     obj.setInvoice_id(rs.getInt("invoice_id")); // Set the invoice_id
		     obj.setIsOpen(rs.getInt("isOpen")); // Set the isOpen
		     obj.setAging_bucket(rs.getString("aging_bucket")); // Set the aging_bucket
		     obj.setIs_deleted(rs.getInt("is_deleted")); // Set the is_deleted
		     data.add(obj); // Add the obj to the list
		   }
		   String json = new Gson().toJson(data); // Create the json
		   
		   response.setContentType("application/json"); // Set the content type
		   response.setStatus(200); // Set the status code to 200 (OK) and send the response to the client
		   response.getWriter().write(json); // Write the json to the response
		   
	} catch(Exception e) { // Catch the exception
		e.printStackTrace(); // Print the exception
	}
  }
}
