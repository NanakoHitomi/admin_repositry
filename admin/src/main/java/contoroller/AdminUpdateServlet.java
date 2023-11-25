package contoroller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class AdminUpdateServlet extends HttpServlet {  //親クラスを継承　
    //HttpServletはさまざまなSクラスを含む。継承することでリストサーブレットはうまく動く

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getAttribute("message") == null) {
            request.setAttribute("message", "todoを管理しましょう");
        }
        
        int postId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String date = request.getParameter("date");
        String priority = request.getParameter("priority");
        

        String url = "jdbc:mysql://localhost/todo";
        String user = "root";
        String password = ""; //mysqlでpwなしに設定されているためpw設定するとダメそう
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "UPDATE posts SET title = ?, content = ?, priority = ?, date = ? WHERE id = ?";  //探したい項目WHEREは一番最後
     
        try (Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement(sql)) {
        	
//        	statement.setString(1, title);
//        	statement.setString(2, content);
//        	statement.setInt(3, postId);
//        	statement.setString(4, priority);
//        	statement.setString(5, date);
        	
        	statement.setString(1, title);
        	statement.setString(2, content);
        	statement.setString(3, priority);
        	statement.setString(4, date);
        	statement.setInt(5, postId);
        	
        	
        	int number = statement.executeUpdate();
        	request.setAttribute("message", "ID:" + postId + "の更新ができました");
        			
            
        } catch (Exception e) {
            request.setAttribute("message", "Exception:" + e.getMessage());
        }


        String forward = "/show?id=" + postId;
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
        dispatcher.forward(request, response);


    }
    
}

