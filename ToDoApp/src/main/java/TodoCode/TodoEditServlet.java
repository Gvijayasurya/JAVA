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

@WebServlet("/todoedit")
public class TodoEditServlet extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String Old_Title = req.getParameter("oldtitle");
		String New_Title = req.getParameter("newtitle");
		String Description = req.getParameter("description");
		String Status = req.getParameter("status");
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","root");
			
			PreparedStatement ps = con.prepareStatement("update task set title=?,description=?,status=? where title=?");
			
			ps.setString(1, New_Title);
			ps.setString(2, Description);
			ps.setString(3, Status);
			ps.setString(4, Old_Title);
			
			ps.executeUpdate();
			
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		resp.sendRedirect("TodoDisplay");
	}
}
