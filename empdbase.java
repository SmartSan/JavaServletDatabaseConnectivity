import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.statement;

public class empdbase extends HttpServlet(HttpRequest request,HttpResponse response)
	throws IOException,ServletException

	//connecting to the database
	
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	PrinterWriter out=response.getWriter();

	try
{
	class.ForName("sun.jdbc.odbc.JdbcOdbcDriver");
	con=DriverManager.getConnection("jdbc.odbc:Employees","","");
}

	catch(ClassNotFoundException e)
{
	e.printStackTrace() ;
}

	catch(Exception e)
{
	e.printStackTrace();
}

try
{
	stmt=con.createStatement();
	rs=stmt.executeQuery("SELECT * FROM Employee_table");

	//displaying records
	
	response.getContentType("text/html");
	out.println("<html>");
	out.println("<head> <title> Servlet Database Connectivity </title> </head>");
	out.println("<body>");
	out.print("<center>");
	out.print("<h2>Employee Database </h2>");
	out.print("<table border = 3>");
	out.print("<th>Emp_ID</th>");
	out.print("<th>Name</th>");
	out.print("<th>Gross</th>");
	out.print("<th>Taxes</th>");
	out.print("<th>Net Salary</th>");
	
	while(rs.next())
	{
		int tot=0;
		String gross=rs.getString(3);
		String deductions=rs.getString(4);

		int g=Integer.parseInt(gross);
		int d=Integer.parseInt(deductions);

		tot=g-d;

		out.print("<tr>");
		out.print("<td>");
		out.print(rs.getInt(1));
		out.print("</td>");
		out.print("<td>");
		out.print(rs.getString(2));
		out.print("</td>");
		out.print("<td>");
		out.print(g);
		out.print("</td>");
		out.print("<td>");
		out.print(d);
		out.print("</td>");
		out.print("<td>");
		out.print(tot);
		out.print("</td>");
		out.print("</tr>");
	}
	out.print("</table>");
	out.print("</center>");
	out.println("</body> </html>");
}

catch(SQLException e){}
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
