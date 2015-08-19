

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class details
 */
@WebServlet("/details")
public class details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public details() {
        super();
        // TODO Auto-generated constructor stub
    }
    public static Connection connectDB() throws SQLException, ClassNotFoundException {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:testuser/password@localhost";
		Properties props = new Properties();
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		Connection conn = DriverManager.getConnection(url, props);
		
		return conn;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custId = request.getParameter("customerID");
		System.out.println("$$$$$$$" + custId);
	
		Connection conn;
		try {
			
			conn = connectDB();
			String sql ="select CUSTOMER_ID, CUST_FIRST_NAME, CUST_LAST_NAME, CUST_CITY, CUST_POSTAL_CODE, CUST_STREET_ADDRESS1, CUST_STREET_ADDRESS2, CUST_EMAIL"
					+ " from demo_customers where customer_ID=" + custId;

	        //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	        ResultSet result = preStatement.executeQuery();
	        //PrintWriter out = response.getWriter();
		String line = "<h3> Customer Information Detailed: </h3>";
		 line += "<table class=" 
	        		+ "\"table table-striped\""
	        		+ ">"; 
	        //String line2 =  "<tr>" 
     		//	+ "<td>" + "<a href" + "=" + "\"details.jsp" +"?" + result.getString("CUSTOMER_ID") + "\"" +">" + "aksjdfsdf" + "</a>" + "</td>";
	        
	        //System.out.println(line2);
	        line += 
     			"<tr>" 
     			+ "<th>" + "id" + "</th>"
     			+"<th>" + "first name" + "</th> <br>"
     			+"<th>" + "last name" + "</th><br>"
     			+"<th>" + "city" + "</th><br>"
     			+"<th>" + "postal code" + "</th><br>"
     			+"<th>" + "address line 1" + "</th><br>"
     			+"<th>" + "address line2" + "</th><br>"
     			+"<th>" + "email" + "</th><br>"
     			+ "</tr>"
     			;
			
	        while(result.next()){
	        	line += "<tr>" 
	        			+ "<td>" +result.getString("CUSTOMER_ID") + "</td>"
	        			+"<td>" + result.getString("CUST_FIRST_NAME") + "</td>"
	        			+"<td>" + result.getString("CUST_LAST_NAME") + "</a></td>"
	        			+"<td>" + result.getString("CUST_CITY")+ "</td>"
	        			+"<td>" + result.getString("CUST_POSTAL_CODE")+ "</td>"
	        			+"<td>" + result.getString("CUST_STREET_ADDRESS1Y")+ "</td>"
	        			+"<td>" + result.getString("CUST_STREET_ADDRESS2")+ "</td>"
	        			+"<td>" + result.getString("CUST_EMAIL")+ "</td>"
	        	        			;

	        }
	        
	        line += "</table>";
     	request.setAttribute("message", line);
			getServletContext().getRequestDispatcher("/customers.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
