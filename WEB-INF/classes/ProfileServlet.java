import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class ProfileServlet extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String name="";
		HttpSession session=request.getSession(false);
		
		if(session!=null)
		{
			String username=(String)session.getAttribute("username");
		 if(!username.equals("")||username!=null)
			{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl","scott","tiger");
				PreparedStatement pst=con.prepareStatement("select * from register where USERNAME=?");
				pst.setString(1,username);
				ResultSet rs=pst.executeQuery();
				rs.next();
				 name=rs.getString("FIRSTNAME")+rs.getString("LASTNAME");
				 //out.print(name);
				
				out.println("<html><head><title>profile page</title><link rel=stylesheet type=text/css href=style1.css></head>"+
"<body><div class=background2><div class=container1><div class=form><h1>MY PROFILE</h1>"+ 
"<form class=my-form action=edit method=POST>"+
"<div class=form-group>"+
"<label>fullname</label>&nbsp;"+
"<input type=text name=full_name value="+name+"></div><div class=form-group><label>email</label>"+
"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
"<input type=text name=email value="+rs.getString("EMAIL")+"></div><div class=form-group><label>username</label>&nbsp;"+
"<input type=text name=user value="+rs.getString("USERNAME")+
"></div><div class=form-group><label>phone no</label>&nbsp;"+
"<input type=text name=phno value="+rs.getString("PHONENO")+
"></div><div class=form-group><label>Address</label>"+
"<textarea style=width:45%>"+rs.getString("ADDRESS")+
"</textarea></div><div class=form-group><label>Age</label>"+
"&nbsp;&nbsp;&nbsp;&nbsp;"+
"<input type=number name=Age value="+rs.getInt("AGE")+
"><br></div><div class=form-group><label>gender</label>&nbsp;"+
"<input type=text name=gender value="+rs.getString("GENDER")+
"></div><a href=home2.html><button class=button>ok</button></a><input class=button type=submit name=Submit value=edit>"+
"</form></div></div></div></body></html>");

pst.close();
con.close();
out.close();
			}
			catch (ClassNotFoundException | SQLException e)
			{
				out.println(e);
			}
		}
		}
		else
		{			    
			out.print("<h2 align=center>your session has been expired!.....<br>");  
            out.print("Please login to continue!.....</h2>");  
            request.getRequestDispatcher("/login.html").include(request, response);  
		}
	
	}
}