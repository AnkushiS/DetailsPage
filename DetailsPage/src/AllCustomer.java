import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/AllCustomer")
public class AllCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllCustomer() {
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
		
		Connection conn;
		try {
			
			conn = connectDB();
			String sql ="select CUSTOMER_ID, CUST_FIRST_NAME, CUST_LAST_NAME, CUST_CITY from demo_customers";

	        //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	        ResultSet result = preStatement.executeQuery();
	        //PrintWriter out = response.getWriter();
	        String line = "<h2> Customer information from Demo customer: </h2>";
	        line += "<table class=" 
	        		+ "\"table table-striped\""
	        		+ "style=width:30%>"; 
	        //String line2 =  "<tr>" 
        		//	+ "<td>" + "<a href" + "=" + "\"details.jsp" +"?" + result.getString("CUSTOMER_ID") + "\"" +">" + "aksjdfsdf" + "</a>" + "</td>";
	        
	        //System.out.println(line2);
	        line += 
        			"<tr>" 
        		//	+ "<th>" + "id" + "</th>"
        			+"<th>" + "first name" + "</th> <br>"
        			//+"<th>" + "last name" + "</th><br>"
        			//+"<th>" + "city" + "</th><br>"
        			+ "</tr>"
        			;
			
	        while(result.next()){
	        	line += "<tr>" 
	        			//+ "<td>" + "<a href" + "=" + "\"details.jsp\"" + ">" + result.getString("CUSTOMER_ID") + "</a></td>"
	        			+"<td>" + "<a href" + "=" + "\"details?customerID=" + result.getString("CUSTOMER_ID") + "\"" + ">"+ result.getString("CUST_FIRST_NAME") + "</a></td>"
	        			//+"<td>" + "<a href" + "=" + "\"details.jsp\"" +">"+ result.getString("CUST_LAST_NAME") + "</a></td>"
	        		//	+"<td>" + "<a href" + "=" + "\"details.jsp\"" + ">" + result.getString("CUST_CITY")+ "</a></td>"
	        			;

	        }
	        
	        line += "</table>";
        	request.setAttribute("message", line);
			getServletContext().getRequestDispatcher("/customers.jsp").forward(request, response);
		
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
