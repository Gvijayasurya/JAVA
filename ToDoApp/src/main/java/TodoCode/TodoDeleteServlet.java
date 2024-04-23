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
@WebServlet("/Tododelete")
public class TodoDeleteServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String Title = req.getParameter("title");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","root");
			
			PreparedStatement ps = con.prepareStatement("Delete from task where title=?");
			
			ps.setString(1,Title);
			
			ps.executeUpdate();
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		resp.sendRedirect("TodoDisplay");
		
	}
}
