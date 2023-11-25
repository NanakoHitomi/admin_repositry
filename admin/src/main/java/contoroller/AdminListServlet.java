package contoroller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class AdminListServlet extends HttpServlet {
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "弊社員");
			
		}
		
		String url = "jdbc:mysql://localhost/officemanagement";
		String user = "root";
		String password = "";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT * FROM users";
		try (Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet results = statement.executeQuery()) {
			ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
			
			while (results.next()) {
				HashMap<String, String> columns = new HashMap<String, String>();
				
				String id = results.getString("id");
				columns.put("id", id);
				
				String name = results.getString("name");
				columns.put("name", name);
				
				String pass = results.getString("password");
				columns.put("password", pass);
				
				String email = results.getString("email");
				columns.put("email", email);
				
				String companyid = results.getString("companyid");
				columns.put("companyid", companyid);
				
				rows.add(columns);
			}
			request.setAttribute("rows", rows);
		} catch (Exception e) {
			request.setAttribute("message", "Exception:" + e.getMessage());
		}
		
		String view = "/WEB-INF/views/adminlist.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
  	}

}
