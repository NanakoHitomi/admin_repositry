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

@WebServlet("/edit")
public class AdminEditServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "今日のタスクは？");
		}
		
		int postId = Integer.parseInt(request.getParameter("id"));
		
		String url = "jdbc:mysql://localhost/todo_practice";
		String user = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 String sql = "SELECT * FROM todolist WHERE id = ?";
	        try (Connection connection = DriverManager.getConnection(url, user, password);
	        PreparedStatement statement = connection.prepareStatement(sql)) {
	        	
	        	statement.setInt(1, postId);
	        	
	        	ResultSet results = statement.executeQuery();
	        			
	            while (results.next()) {

	                String id = results.getString("id");
	                request.setAttribute("id", id);

	                String title = results.getString("title");
	                request.setAttribute("title", title);
	                
	                String duedata = results.getString("duedata");
	                request.setAttribute("duedata", duedata);
	                
	                String status = results.getString("status");
					request.setAttribute("status", status);
					
					String content = results.getString("content").replaceAll("¥n", "<br>");
					request.setAttribute("content", content);
	                

	            }
	        } catch (Exception e) {
	            request.setAttribute("message", "Exception:" + e.getMessage());
	        }


	        String view = "/WEB-INF/views/edit.jsp";
	        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
	        dispatcher.forward(request, response);


	    }
	    
	}
