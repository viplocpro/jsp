package forms;

import java.io.*;
import java.util.regex.Pattern;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/room-change")
public class RoomChange extends HttpServlet {
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession mySession = request.getSession();
		String username = (String)mySession.getAttribute("user");
		
		if (username!=null)
		{
			String result=CheckRoomRules(GetUserInfos(username),request.getParameter("room"),response,mySession);
			if(!result.equals(""))
				response.sendRedirect("room-list?result="+result);
		}
		else
		{
			response.sendRedirect("login");
		}
		
		 
	}
	
	private String[] GetUserInfos(String username){
		String[] userInfos= new String[6];
		BufferedReader br = null;

		try 
		{
			String currentLine ="";
			br = new BufferedReader
				    (new InputStreamReader(new FileInputStream("F:\\ChatDatabase.txt"),"UTF-8"));

			while ((currentLine = br.readLine()) != null){
				 
				String[] currentInfos = currentLine.split(Pattern.quote("^")); //username co trong database
				String dbUsername = currentInfos[0]; //username
				
				if (dbUsername.equals(username))
				{
					
					try {
						if (br != null)
							br.close();
						return currentInfos;
					} 
					catch (IOException ex) {
						ex.printStackTrace();
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
		
		
		return userInfos;
	}
	
	private String CheckRoomRules(String[] infos, String room,HttpServletResponse response, HttpSession mySession) throws IOException{
		
		String age = infos[2]; //age
		String gender = infos[3]; //gender
		String status = infos[4]; //status
		String result = "";
		
		if(mySession.getAttribute("room")!=null)
			mySession.removeAttribute("room");
		
		switch(room){
			case "0": {
				if(gender.equals("1"))
				{
					mySession.setAttribute("room", room);
					response.sendRedirect("room-selected");
					return result;
				}
				return result=room;
			}
			case "1": {
				if(gender.equals("0"))
				{
					mySession.setAttribute("room", room);
					response.sendRedirect("room-selected");
					return result;
				}
				return result=room;
			}
			case "2": {
				if(status.equals("0") && Integer.parseInt(age)>=16)
				{
					mySession.setAttribute("room", room);
					response.sendRedirect("room-selected");
					return result;
				}
				return result=room;
				
			}
			case "3": {
				if(Integer.parseInt(age)>=13 && Integer.parseInt(age)<=19)
				{
					mySession.setAttribute("room", room);
					response.sendRedirect("room-selected");
					return result;
				}
				return result=room;
			}
			case "4": {
				mySession.setAttribute("room", room);
				response.sendRedirect("room-selected");
				return result;
			}
		}
		
		return result;
	}
}

