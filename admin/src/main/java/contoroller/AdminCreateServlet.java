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

@WebServlet("/create")
public class AdminCreateServlet extends HttpServlet {  //親クラスを継承　
    //HttpServletはさまざまなSクラスを含む。継承することでリストサーブレットはうまく動く

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getAttribute("message") == null) {
            request.setAttribute("message", "今日のタスクは？");
        }
        
       
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String duedata = request.getParameter("duedata");
        String status = request.getParameter("status");

        String url = "jdbc:mysql://localhost/todo_practice";
        String user = "root";
        String password = ""; //mysqlでpwなしに設定されているためpw設定するとダメそう
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO posts (title,duedata,status,content) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement(sql)) {
        	
        	statement.setString(1, title);
        	statement.setString(2, duedata);
        	statement.setString(3, status);
        	statement.setString(4, content);
        	
      
        	int number = statement.executeUpdate();
        	request.setAttribute("message", "タイトル:" + title + "の新規作成ができました");
        			
            
        } catch (Exception e) {
            request.setAttribute("message", "Exception:" + e.getMessage());
        }


        String forward = "/list";
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
        dispatcher.forward(request, response);


    }
    
}

