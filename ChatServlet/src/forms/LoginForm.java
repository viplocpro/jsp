package forms;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginForm extends HttpServlet {
	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
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
	      + "<body>"
	      + "<div class=\"container\">"
	      	+ "<div class=\"row\">"
	      		+ "<div class=\"col-md-4\"></div>"+
	      			"<div class=\"col-md-4 margin-top-10\">"+
	      				"<div class=\"form-heading\"><h2>Đăng nhập</h2></div>"
	  				+ "<div class=\"login-form\">"
	  					+ "<form method=\"POST\" action=\"login-result\">"
	  						+ "<div class=\"form-group has-feedback has-feedback-left\">"+
	  						SetCorrectSolutionInput(GetParameters(request,response),"username")+
	      							"<span class=\"glyphicon glyphicon-user form-control-feedback\"></span>"+
	      							"</div>"+
	      					  "<div class=\"form-group has-feedback\">"+
	      					  	SetCorrectSolutionInput(GetParameters(request,response),"password")+
	      					  	"<span class=\"glyphicon glyphicon-lock form-control-feedback\"></span>"
	  					  	 + "</div>"
	  					  	 + AlertResult(request,response)
							
	  					  	 + "<div class=\"form-group\">"
	  					  	 	+ "<label>Chưa có tài khoản ? <a href=\"register\">Đăng ký</a></label>"
	  					  	 + "</div>"+
					  	 	"<div class=\"form-group\">"+
					  	 		"<button class=\"btn-submit btn-block\" type=\"submit\">Tới</button>"+
				  	 		"</div>"
			  	 		+ "</form>"
		  	 		+ "</div>"
	  	 		+ "</div>"+
	  			"<div class=\"col-md-4\"></div>"
			+ "</div>"
		+ "</div></body></html>");
	}
	
private String SetCorrectSolutionInput(String[] parameters, String name){
		
		String result="";
		
		if (name.equals("username"))
		{
			if (parameters[0]==null)
				return result="<input class=\"form-control\" type=\"text\" name=\"username\" placeholder=\"Tên đăng nhập\" autofocus>";
			if (parameters[0].equals(""))
				return result="<input class=\"form-control missing\" type=\"text\" name=\"username\" placeholder=\"Nhập Tên đăng nhập vô đi thím\" autofocus>";
			else
				return result="<input class=\"form-control\" type=\"text\" name=\"username\" value=\""+parameters[0]+"\" placeholder=\"Tên đăng nhập\" autofocus>";
		}
		if (name.equals("password"))
		{
			if (parameters[1]==null)
				return result="<input class=\"form-control\" type=\"password\" name=\"password\" placeholder=\"Mật khẩu\">";
			if (parameters[1].equals(""))
				return result="<input class=\"form-control missing\" type=\"password\" name=\"password\" placeholder=\"Nhập Mật khẩu vô đi thím\" autofocus>";
			else
				return result="<input class=\"form-control\" type=\"password\" name=\"password\" value=\""+parameters[1]+"\" placeholder=\"Mật khẩu\">";
		}
		return result;
		
	}
	
	private String AlertResult(HttpServletRequest request, HttpServletResponse response){
		String result = request.getParameter("result");
		String username = request.getParameter("username");
		String alert = "";
		if (result!=null)
		{
			if (result.equals("failed"))
			{
				return alert="<div class=\"alert alert-danger\">Tên đăng nhập hoặc Mật khẩu không chính xác</div>";
			}
			if (result.equals("success"))
			{
				return alert="<div class=\"alert alert-success\">Bạn đã đăng nhập thành công với tên <strong>"+username+"</strong></div>";
			}
			return "";
		}
		else
		{
			return alert="<div class=\"alert alert-danger hidden\">Tên đăng nhập bạn vừa đăng ký đã tồn tại</div>";
		}
	}
	
	private String[] GetParameters(HttpServletRequest request, HttpServletResponse response) {
		String[] parameters = new String[2];
		
		parameters[0] = request.getParameter("username");
		parameters[1] = request.getParameter("password");
		
		return parameters;
	}
	
}

