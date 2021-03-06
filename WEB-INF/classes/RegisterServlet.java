import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet 
{
PrintWriter out;
Connection con;
PreparedStatement pst;
PreparedStatement pst1;
  public void doPost(HttpServletRequest request,HttpServletResponse response)throws 
	  ServletException,IOException
	  { 
	   response.setContentType("text/html");
	  out=response.getWriter();
String username=request.getParameter("user_name");
String email=request.getParameter("email");
String first_name=request.getParameter("first_name");
String last_name=request.getParameter("last_name");
String pwd=request.getParameter("pwd");
String phoneno=request.getParameter("phno");
int age=Integer.parseInt(request.getParameter("Age"));
String address=request.getParameter("address");
String gender=request.getParameter("gender");
	 
	 
    try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
     con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl","scott","tiger");
con.setAutoCommit(false);



pst1=con.prepareStatement("select * from register where USERNAME=? or EMAIL=?");
pst1.setString(1,username);
pst1.setString(2,email);
ResultSet rs=pst1.executeQuery();

if(rs.next())
{

 out.println("<body text=red><h1 align=center color=red>USERNAME OR EMAILID ALREADY EXISTS</h1></body>");
 
 RequestDispatcher rd=request.getRequestDispatcher("/register.html");

 rd.include(request,response);
}	
else
{

	  pst=con.prepareStatement("insert into register values(?,?,?,?,?,?,?,?,?)");

	  pst.setString(1,first_name); 
	  pst.setString(2,last_name); 
	  pst.setString(3,email);
	  pst.setString(4,username); 
	  pst.setString(5,pwd); 
	  pst.setString(6,phoneno); 
	  pst.setInt(7,age); 
	  pst.setString(8,address); 
	  pst.setString(9,gender);

	  pst.executeUpdate();
	  	  
	 out.println("<body text=red><h1 align=center>Registered successfully</h1></body>");
	 con.commit();
	 RequestDispatcher rd=request.getRequestDispatcher("/home1.html");
	 rd.include(request,response);
	 //response.sendRedirect("/go_bus/home1.html");
}
	 
		}
		catch(SQLException|ClassNotFoundException e)
		{
		 out.println(e);
		}
		finally
		  {
			try
			{
				if(pst!=null)
				{
					pst.close();
				}
			}
			catch (SQLException e)
			{
				out.println(e);
			}
			try
			{
				if(con!=null)
				{
					con.close();
				}
			}
			catch (SQLException e)
			{
				out.println(e);
			}												   
			finally
			  {
				out.close();
			  }
		  }
		
		


	}

}