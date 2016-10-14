package forms;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/write-chat-content")
public class WriteChatContent extends HttpServlet {
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession mySession = request.getSession();
		
		String username = (String)mySession.getAttribute("user");
		if (username==null)
		{
			response.sendRedirect("login");
		}
		else
		{	
			String chat = request.getParameter("chat-input");
			if(WriteChatContent(username,chat,mySession))
				response.sendRedirect("room-selected");
			else
				response.sendRedirect("login");
		}
	}
	
	private boolean WriteChatContent(String username,String chatContent,HttpSession mySession){
		BufferedWriter writer = null;
		String[] rooms = {"Male","Female","Dating","Teen","General,ChatContent"};
		String room = (String)mySession.getAttribute("room");
		String sessionUsername = (String)mySession.getAttribute("user");
		
		if (!chatContent.trim().equals(""))
		{
			try
			{
				writer = new BufferedWriter
					    (new OutputStreamWriter(new FileOutputStream("F:\\"+rooms[Integer.parseInt(room)]+".txt",true),"UTF-8"));
			    
			    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			    Date date = new Date();
			    
			    writer.write("\r\n"+username+"^"+chatContent+"^"+dateFormat.format(date));
			}
			catch ( IOException e)
			{
				return false;
			}
			finally
			{
			    try
			    {
			        if ( writer != null)
			        writer.close( );
			        
			    }
			    catch ( IOException e)
			    {
			    	
			    }
			}    	
		}
		return true;
		
	}
	
}

