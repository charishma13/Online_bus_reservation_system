import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LogOutServlet extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");

		HttpSession session=request.getSession();
		session.invalidate();

		out.print("<h2 align=center>logout successful!...</h2>");
		request.getRequestDispatcher("/home1.html").include(request,response);

		out.close();
	}
}