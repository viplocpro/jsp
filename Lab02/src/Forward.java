

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

@WebServlet("/forward")
public class Forward extends HttpServlet {
	
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
		  PrintWriter out = response.getWriter();
		  String F = request.getParameter("F");
		  
		  
		  response.setContentType("text/html;charset=utf-8");
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
			  out.println("<h2>Tham số <strong>"+F + "</strong> đã nhập không đúng định dạng. Nhập số thôi má ơiii</h2>");
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