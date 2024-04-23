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
@WebServlet("/todoregistration")
public class TodoRegistrationServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String name = req.getParameter("fname");
		String email = req.getParameter("email");
		String Phone = req.getParameter("number");
		String gender = req.getParameter("gender");
		String DoB = req.getParameter("dob");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","root");
			
			PreparedStatement ps = con.prepareStatement("insert into credential values(?,?,?,?,?,?,?)");
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, Phone);
			ps.setString(4, gender);
			ps.setString(5, DoB);
			ps.setString(6, username);
			ps.setString(7, password);
			
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		resp.sendRedirect("TodoLogin.html");
	}
}
