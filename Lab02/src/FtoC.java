

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/** Servlet that prints out the param1, param2, and param3
 *  request parameters. Does not filter out HTML tags.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

@WebServlet("/FtoC")
public class FtoC extends HttpServlet {
	
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	  PrintWriter out = response.getWriter();
	  out.println
      ("<!DOCTYPE html>"+
      "<html><head><meta charset=\"utf-8\">"+
      	"<title>Chat Room Servlet</title>"+
      	"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.min.css\">"+
      	"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap-theme.min.css\">"+
      	"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/custom.css\">"+
      	"<script src=\"jquery.min.js\" type=\"text/javascript\"></script>"+
      	"<script src=\"js/bootstrap.min.js\" type=\"text/javascript\"></script>"+
      	"</head>"
      + "<body>");
	  		
		  
		  String F = request.getParameter("F");
		  F=F.trim();
		  if (F!=null &&F.length()>0){
			  if (isNumeric(F))
			  {
				  double C = (double)Math.round(((Float.parseFloat(F)-32)/1.8)*100)/100;
				  
				  out.println("<h2>"+F + " Fahrenheit = "+C+" Celsius</h2>");
				  
			  }
			  else
			  {
				  
				  request.setAttribute("F", F);
				  request.getRequestDispatcher("/forward").forward(request, response);
			  }
		  }
		  else{
			  out.println("<h2>Nhập khoảng trắng thì có gì mà tính ???</h2>");
		  }
		  out.println("</body>"
				  + "</html>");
	  }
  
  @Override
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		 doGet(request,response);
	  }
  
  private boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	} 
  
  }