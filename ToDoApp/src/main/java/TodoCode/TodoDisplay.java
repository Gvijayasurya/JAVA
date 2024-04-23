package TodoCode;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/TodoDisplay")
public class TodoDisplay extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from task");
			
			//create a table using html
			PrintWriter pw = resp.getWriter();
			resp.setContentType("text/html");
			
			pw.print("<html><body>");
			pw.print("<h3>Task DETAILS<h3>");
			pw.print("<table border=1><tr>"+"<th>Title</th>"
					+"<th>Description</th>"
					+"<th>Status</th>"
					+"<th>Edit</th>"
					+"<th>Delete</th>"+"</tr>");
			
			//retrieving values from data base
			
			while(rs.next())
			{
				String title = rs.getString(1);
				String description = rs.getString(2);
				String status = rs.getString(3);
				
				pw.print("<tr>"+"<td>"+title+"</td>"
						+"<td>"+description+"</td>"
						+"<td>"+status+"</td>"
						+"<td><a href=TodoEdit.html><button>Edit</button></a></td>"
						+"<td><a href=TodoDelete.html><button>Delete</button></a></td>"
						+"</tr>");
				
			}
			pw.print("</table> <br><br> <button><a href='TodoHome.html'>Home</a></button> <button><a href='TodoLogin.html'>Logout</a></button></body></html>");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
