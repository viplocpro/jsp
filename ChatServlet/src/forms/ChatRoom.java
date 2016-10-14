package forms;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/chat-room")
public class ChatRoom extends HttpServlet {
	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession mySession = request.getSession();
		String username = (String)mySession.getAttribute("user");
		String currentUser = username;
		if (username==null)
		{
			response.sendRedirect("login");
		}
		else
		{	
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
		      		+ "<div class=\"col-md-3\"></div>"+
		      			"<div class=\"col-md-6 margin-top-5\">"+
		      			 "<div class=\"currentUser\">"
		      				+"<a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span><strong>    "+currentUser+"    </strong></a><form style=\"float:right;\" method=POST action=\"log-out\"><button type=submit class=\"non-btn\">[Thoát]</button></form>"
		      			+"</div>"
		      			+"<div class=\"form-heading\"><h2>Thổ lộ đi nào!</h2></div>"
		  				+ "<div class=\"chat-box\">"
		  				+ "<div class=\"alert alert-info new-message\" onclick=\"Scroll();\">"
		  					+ "<strong>Bạn có tin nhắn mới! Nhấp để xem.</strong>"
		  				+ "</div>"
		  				  + "<div class=\"chat-content scrollable\">");
		  				  		ReadChatContent(request,response);
		  				  out.println("</div>"
		  				  + "<br>"
		  				+"<form method=\"POST\" action=\"write-chat-content\">"
		  				  	+ "<div class=\"form-group chat-input\">"
		  				  		+ "<input type=text name=\"chat-input\" id=\"submit-chat\" class=\"form-control input-chat\" placeholder=\"Nhập tin nhắn...\" autocomplete=\"off\" autofocus>"
					  		+ "</div>"
		  				 +"</form>"
			  	 		+ "</div>"
		  	 		+ "</div>"+
		  			"<div class=\"col-md-3\">"
				+ "</div>"
			+ "</div>"
			+ "<script src=\"js/custom.js\" type=\"text/javascript\"></script>"
			+ "<script>"
				+ "var refInterval = window.setInterval((function update() {"
					+"$.ajax({"+
				        "type : 'POST',"+
				        "url : 'update-chat-content',"+
				        "success : function(data){"+
				        "var objDiv = document.getElementsByClassName(\"chat-content\")[0];"
				        + "var scroll=false;"
				        + "var lastHeight=objDiv.scrollHeight;"
				        + "if (objDiv.scrollTop == objDiv.scrollHeight-objDiv.clientHeight)"
				        	+ "scroll=true;"+
				        	"objDiv.innerHTML=data;" 
				            + "if(scroll)"
				            	+ "objDiv.scrollTop = objDiv.scrollHeight;"
			            	+ "else"
			            		+ "{"
			            		+ "if(objDiv.scrollHeight>lastHeight)"
			            			+ "{"
			            				+ "DisplayNewMsg();"
			            			+ "}"
		            			+ "}"+
				        "},"+
				    "}).then(function() {"+
					       "setTimeout(update, 1000);"+
					    "})})(), 1000);"
				+ ""
			+ "</script>"
			+ "</body></html>");
		}
	}
	
	
	private void ReadChatContent (HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		HttpSession mySession = request.getSession();
		String sessionUsername = (String)mySession.getAttribute("user");
		
		BufferedReader br = null;
		try 
		{
			String currentLine ="";
			br = new BufferedReader
				    (new InputStreamReader(new FileInputStream("F:\\ChatContent.txt"),"UTF-8"));
			
			while ((currentLine = br.readLine()) != null){
				if (currentLine.trim().length()>1)
				{
					String[] aLine = currentLine.split(Pattern.quote("^"));
					
					String dbUsername=aLine[0];
					String date=aLine[aLine.length-1];
					String chatContent="";
					
					for (int i=1; i< aLine.length-1; i++) //xử lý khi nội dung chat có dấu chứa dấu ^
					{
						if (i==aLine.length-2)
							chatContent+=aLine[i];
						else
							chatContent+=aLine[i]+"^";
					}
					
					if (dbUsername.equals(sessionUsername))
					{
						out.println(""
						+ "<div class=\"your-msg\">"
					  			+"<div class=\"username\">"
					  				+ "<span>"+dbUsername+":</span>"
				  				+ "</div>"
				  				+ "<div class=\"msg your-msg-color\">"
				  				+ "<span>"+chatContent+"</span>"
			  				+ "</div>"
			  			+ "<div class=\"time your-time\">"
					  			+date
				  		  +"</div>"
				  		+"</div>");
					}
					else
					{
						out.println(""
						+ "<div class=\"others-msg\">"
					  			+"<div class=\"username\">"
					  				+ "<span>"+dbUsername+":</span>"
					  			+ "</div>"
					  			+ "<div class=\"msg others-msg-color\">"
					  			+ "<span>"+chatContent+"</span>"
					  		+ "</div>"
					  		+ "<div class=\"time others-time\">"
					  			+date
			  				  +"</div>"
		  				+ "</div>");
					}
				}
			}
			
		} 
		
		catch (IOException e) {
			e.printStackTrace();

		} 
		
		finally 
		{
			try {
				if (br != null)
					br.close();
			} 
			
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
		
	}
	
}

