import java.io.*;
import java.util.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DisplayEmp extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response)
		throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head> <title>Servlet Database Connectivity</title></head>");
		out.println("<body>");

		//connecting to database
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			String dbURL="jdbc:oracle:oci@my_database";
			Properties.properties=new Properties();
			properties.put("user","scott");
			properties.put("password","tiger");
			properties.put("defaultRowPrefetch","20");
			
			con=DriverManager.getConnection (dbURL.properties);
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from Employee");

			out.print("<table border=1>");
			out.print("<tr><td>EmployeeID</td><td>Name<td></td>");

			while(rs.next())
			{
				//Retrieve by Column name
				
				int id=rs.getInt("ID");
				String name=rs.getString("Name");
				out.print("<tr><td>"+id"</td></tr>"+name);
				out.print("</td></tr>");
			}
			out.println("</table></body></html>");
		}

		catch(SQLException e)
		{
			throw new ServletException("Servlet Could not be display records.",e);
		}

		catch(ClassNotFoundException e)
		{
			throw new ServletException("JDBC Driver not found.",e);
		}

		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
					rs=null;
				}
				if(stmt!=null)
				{
					stmt.close();
					stmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
			}
			catch(SQLException e){}
		}
		out.close();
	}
}
