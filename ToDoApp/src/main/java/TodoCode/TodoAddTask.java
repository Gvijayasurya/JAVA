package TodoCode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Todoaddtask")
public class TodoAddTask extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String Title = req.getParameter("title");
		String Description = req.getParameter("description");
		String Status = req.getParameter("status");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","root");
			
			PreparedStatement ps = con.prepareStatement("insert into task values(?,?,?)");
			
			ps.setString(1,Title);
			ps.setString(2, Description);
			ps.setString(3,Status);
			
			ps.executeUpdate();
		} 
		catch (Exception e) 
		{

			e.printStackTrace();
		}
		resp.sendRedirect("TodoHome.html");
		
	}
	

}
