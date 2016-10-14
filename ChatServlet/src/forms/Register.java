package forms;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.lang.*;

@WebServlet("/register")
public class Register extends HttpServlet {
	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String[] parameters = GetParameters(request,response);

		 out.println
	      ("<!DOCTYPE html>"+
	      "<html><head><meta charset=\"utf-8\">"+
	      	"<title>Chat Room Servlet</title>"
	      	+RedirectAfterRegisterSuccess(request.getParameter("result")) +
	      	"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.min.css\">"+
	      	"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap-theme.min.css\">"+
	      	"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/custom.css\">"+
	      	"<script src=\"jquery.min.js\" type=\"text/javascript\"></script>"+
	      	"<script src=\"js/bootstrap.min.js\" type=\"text/javascript\"></script>"+
	      "</head>"
	      + "<body>"
	      + "	<div class=\"container\">"
	      + "		<div class=\"row\">"
	      + "			<div class=\"col-md-4\"></div>"+
	      				"<div class=\"col-md-4 margin-top-10\">"+
	      					"<div class=\"form-heading\"><h2>Đăng Ký Thành Viên</h2></div>"
	      				  + "<div class=\"register-form\">"
	      				  	+ "<form method=\"POST\" action=\"register-result\">"
	      				  	+ "	<div class=\"form-group has-feedback has-feedback-left\">"+
	      				  			SetCorrectSolutionInput(parameters,"username")+
	      				  			"<span class=\"glyphicon glyphicon-user form-control-feedback\"></span>"+
	      				  	  "</div>"+
	      					"<div class=\"form-group has-feedback\">"+
	      							SetCorrectSolutionInput(parameters,"password")+
	      						"<span class=\"glyphicon glyphicon-lock form-control-feedback\"></span>"
	  					  + "</div>"
	  					  +AlertResult(request,response)+
	      					"<div class=\"radio\">"
	      					+ "<div class=\"row\">"
	      					+ "	<div class=\"col-md-6\">"
	      					+ "		<label class=\"radio-inline\"><input type=\"radio\" name=\"gender\" value=\"1\" checked>Nam</label>"
	      					  + "</div>"+
	      					   "<div class=\"col-md-6\">"+
	      							"<label class=\"radio-inline\"><input type=\"radio\" name=\"gender\" value=\"0\">Nữ</label>"+
	      					    "</div>"
	      				    + "</div>"
	      			     + "</div>"
	      			     + SetCorrectSolutionInput(parameters,"age")+
	      					"<div class=\"form-group form-select\">"
	      						+ "<label>Hiện trạng của bạn:</label>"+
	      						"<select name=\"status\" class=\"form-control\">"
	      							+ "<option selected value=\"0\">Ế</option>"
	      							+ "<option value=\"1\">Đã có chủ</option>"
	      						+ "</select>"+
	      					"</div>"
	      					+ "<div class=\"form-group\">"
  					  	 	+ "<label>Đã có tài khoản ? <a href=\"login\">Đăng nhập</a></label>"
  					  	 + "</div>"
	      				+ "<div class=\"form-group\">"+
	      						"<button class=\"btn-submit btn-block\" type=\"submit\">Lên</button>"+
	    				  "</div>"
	    		     + "</form>"
	    	       + "</div>"
	            + "</div>"+
	         "<div class=\"col-md-4\"></div>"
	      + "</div>"
	   + "</div>"
	+ "</body>"
	   + "</html>");
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
				return result="<input class=\"form-control\" type=\"text\" name=\"username\" value=\""+parameters[0]+"\" placeholder=\"Tên đăng nhập\">";
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
		if (name.equals("age"))
		{
			if (parameters[2]==null)
				return result="<input class=\"form-control\" type=\"text\" name=\"age\" placeholder=\"Tuổi\">";
			if (parameters[2].equals(""))
				return result="<input class=\"form-control missing\" type=\"text\" name=\"age\" placeholder=\"Nhập Tuổi vô đi thím\" autofocus>";
			if (parameters[2].equals("wrong"))
				return result="<input class=\"form-control missing\" type=\"text\" name=\"age\" placeholder=\"Tuổi không hợp lệ <từ 1 đến 100>\" autofocus>";
			else
				return result="<input class=\"form-control\" type=\"text\" name=\"age\" value=\""+parameters[2]+"\" placeholder=\"Tuổi\">";
		}
		return result;
		
	}
	
	private String AlertResult(HttpServletRequest request, HttpServletResponse response){
		String result = request.getParameter("result");
		String username=request.getParameter("username");
	
		String alert = "";
		if (result!=null)
		{
			if (result.equals("exist"))
			{
				return alert="<div class=\"alert alert-danger\">Tên đăng nhập <strong>"+username+"</strong> đã tồn tại</div>";
			}
			if (result.equals("success"))
			{
				return alert="<div class=\"alert alert-success\">Bạn đã đăng ký thành công với tên <strong>"+username+"</strong>. Bạn sẽ được chuyển sang trang <strong>Đăng Nhập</strong> trong 3 giây nữa...</div>";
			}
			if (result.equals("error"))
			{
				return alert="<div class=\"alert alert-danger\">Lỗi không xác định</div>";
			}
			return "";
		}
		else
		{
			return alert="";
		}
	}
	
	private String[] GetParameters(HttpServletRequest request, HttpServletResponse response) {
		String[] parameters = new String[5];
		
		parameters[0] = request.getParameter("username");
		parameters[1] = request.getParameter("password");
		parameters[2] = request.getParameter("age");
		parameters[3] = request.getParameter("gender");
		parameters[4] = request.getParameter("status");
		
		return parameters;
	}
	
	private String[] replaceNull(String[] parameters) {
		for (int i=0; i<parameters.length;i++)
	    {
		    if(parameters[i] == null) 
		    	parameters[i]="";
	    }
		return parameters;
	}
	
	private String RedirectAfterRegisterSuccess (String result){
		if (result!=null)
			if (result.equals("success"))
				return "<meta http-equiv=\"Refresh\" content=\"3;url=login\">";
		return "";
	}
}

