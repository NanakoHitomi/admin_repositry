package contoroller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/show")
public class AdminShowServlet extends HttpServlet {
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "社員中身");
			
		}
		
		int userId = Integer.parseInt(request.getParameter("id"));
//		int positionId = Integer.parseInt(request.getParameter("gradeid"));
		
		System.out.println(userId);
		String url = "jdbc:mysql://localhost/officemanagement";
		String user = "root";
		String password = "";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT company.id, position.grade_name, company.department FROM company, position WHERE company.grade = position.gradeid AND company.id = ? ";
		try (Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.setInt(1, userId);
			
			
			
			ResultSet results = statement.executeQuery();
			
			while (results.next()) {
				
				String company_id = results.getString("company.id");
				request.setAttribute("company.id", company_id);
				
				String grade_name = results.getString("position.grade_name");
				request.setAttribute("position.grade_name", grade_name);
				
				String department = results.getString("company.department");
				request.setAttribute("company.department", department);
				
//				String id = results.getString("id");
//				request.setAttribute("id", id);
//				
//				String grade = results.getString("grade");
//				request.setAttribute("grade", grade);
//				
//				
//				String department = results.getString("department");
//				request.setAttribute("department", department);
				
//				String status = results.getString("status");
//				request.setAttribute("status", status);
//				
//				String content = results.getString("content").replaceAll("¥n", "<br>");
//				request.setAttribute("content", content);
				
				
			}
		} catch (Exception e) {
			request.setAttribute("message", "Exception:" + e.getMessage());
		}
		
		String view = "/WEB-INF/views/adminshow.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
  	}

}
